package com.saleshub.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import com.saleshub.config.security.SecurityConfig;
import com.saleshub.config.security.UserSpringSecurity;
import com.saleshub.domain.enums.CustomerProfile;
import com.saleshub.services.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.saleshub.domain.Address;
import com.saleshub.domain.City;
import com.saleshub.domain.Customer;
import com.saleshub.domain.dto.CustomerDTO;
import com.saleshub.domain.dto.CustomerNewDTO;
import com.saleshub.domain.enums.ClientType;
import com.saleshub.repositories.CustomerRepository;
import com.saleshub.services.exceptions.DataIntegrityException;
import com.saleshub.services.exceptions.ObjectNotFoundException;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;
	@Autowired
	private S3Service s3Service;
	@Autowired
	private ImageService imageService;
	@Value("${image.prefix.customer.profile}")
	private String customerProfilePrefix;
	@Value("${image.profile.size}")
	private Integer profileImageSize;

	public Customer create(Customer customer) {
		customer.setId(null);
		return this.repository.saveAndFlush(customer);
	}

	public void save(Customer customer){
		this.repository.saveAndFlush(customer);
	}

	public Customer update(Customer customer, Integer id) {
		
		findById(id);
		
		customer.setId(id);
		
		return this.repository.saveAndFlush(customer);
	}

	public void deleteById(Integer id) {
		findById(id);
		try {
			this.repository.deleteById(id);
		} catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir um cliente com pedidos "
					+ "em andamento.");
		}
	}
	
	public Customer findById(Integer id) {

		UserSpringSecurity authenticatedUser = UserService.userAuthenticated();

		if(authenticatedUser == null || !authenticatedUser.hasRole(CustomerProfile.ADMIN)
		&& !id.equals(authenticatedUser.getId())){

			throw new AuthorizationException("Acesso negado");
		}

		return this.repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado. Id: " + id));
	}

	public Customer findByEmail(String email){
		return this.repository.findByEmail(email);
	}

	public Customer findByAuthenticatedUserEmail(String email) {

		UserSpringSecurity authenticatedUser = UserService.userAuthenticated();

		if(authenticatedUser == null ||
				!authenticatedUser.hasRole(CustomerProfile.ADMIN) && !email.equals(authenticatedUser.getUsername())){
			throw new AuthorizationException("Acesso negado");
		}

		Customer customer = this.repository.findByEmail(email);

		if(customer == null){
			throw new ObjectNotFoundException("Cliente não encontrado. Id: " + authenticatedUser.getId() +
					", Tipo: " + Customer.class.getName());
		}

		return customer;
	}

	public List<Customer> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest
				.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return this.repository.findAll(pageRequest);
	}
	
	public Customer toCustomer(CustomerDTO customerDTO) {
		return new Customer(customerDTO.getId(), customerDTO.getName(),
				customerDTO.getEmail(),null,null,null);
	}
	
	public Customer toCustomer(CustomerNewDTO customerNewDTO) {
		
		Customer customer = new Customer(null,customerNewDTO.getName(),customerNewDTO.getEmail(),
				customerNewDTO.getDocument(),ClientType.toEnum(customerNewDTO.getType()),
				SecurityConfig.bCryptPasswordEncoder().encode(customerNewDTO.getPassword()));
		
		City city = new City(customerNewDTO.getCityId(),null,null);
		
		Address address = new Address(null,customerNewDTO.getPublicPlace(),customerNewDTO.getNumber(),
				customerNewDTO.getComplement(),customerNewDTO.getNeighborhood(),
				customerNewDTO.getZipCode(),customer,city);
		
		customer.getAddresses().add(address);
		customer.getPhones().add(customerNewDTO.getPhone1());
		customer.getPhones().add(customerNewDTO.getPhone2());
		customer.getPhones().add(customerNewDTO.getPhone3());
		
		return customer;
	}

	public URI uploadProfilePicture(MultipartFile multipartFile){

		UserSpringSecurity authenticatedUser = UserService.userAuthenticated();

		if(authenticatedUser == null){
			throw new AuthorizationException("Acesso negado");
		}

		String customerProfilePrefix =
				this.customerProfilePrefix + authenticatedUser.getId() + ".jpg";

		BufferedImage image = prepareProfileImageToBeUploaded(multipartFile);

		return this.s3Service.uploadFile(
				this.imageService.getInputStream(image, "jpg"),
				customerProfilePrefix,
				"image");
	}

	private BufferedImage prepareProfileImageToBeUploaded(MultipartFile multipartFile){
		BufferedImage jpgImage = this.imageService.getJPEGImageFromFile(multipartFile);

		BufferedImage cropedImage = this.imageService.cropImage(jpgImage);

		BufferedImage resizedImage = this.imageService.resizeImage(cropedImage, profileImageSize);

		return resizedImage;
	}
}
