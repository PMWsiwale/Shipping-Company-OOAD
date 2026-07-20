package users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void customerRegister(CustomerReg register)
	{
		userRepository.customerRegister(register);
	}
	
	public void supplierRegister(SupplerReg register)
	{
		userRepository.SupplierRegister(register);
	}
	
	public void employeeRegistration(Employee register)
	{
		userRepository.employeeRegister(register);
	}

	
}
