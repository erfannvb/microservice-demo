package nvb.dev.accounts.service.impl;

import lombok.RequiredArgsConstructor;
import nvb.dev.accounts.constants.AccountsConstants;
import nvb.dev.accounts.dto.AccountsDto;
import nvb.dev.accounts.dto.CustomerDto;
import nvb.dev.accounts.entity.AccountsEntity;
import nvb.dev.accounts.entity.CustomerEntity;
import nvb.dev.accounts.exception.CustomerAlreadyExistsException;
import nvb.dev.accounts.exception.ResourceNotFoundException;
import nvb.dev.accounts.mapper.AccountsMapper;
import nvb.dev.accounts.mapper.CustomerMapper;
import nvb.dev.accounts.repository.AccountsRepository;
import nvb.dev.accounts.repository.CustomerRepository;
import nvb.dev.accounts.service.AccountsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        CustomerEntity customerEntity = CustomerMapper.mapToCustomerEntity(customerDto);

        Optional<CustomerEntity> optionalCustomer = customerRepository.findByMobileNumber(customerDto.mobileNumber());
        if (optionalCustomer.isPresent())
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number: " + customerDto.mobileNumber());

        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        CustomerEntity customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        AccountsEntity accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        return CustomerMapper.mapToCustomerDto(customer, AccountsMapper.mapToAccountsDto(accounts));
    }

    private AccountsEntity createNewAccount(CustomerEntity customerEntity) {
        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setCustomerId(customerEntity.getCustomerId());

        long randomAccountNumber = new Random().nextInt(900000);

        accountsEntity.setAccountNumber(randomAccountNumber);
        accountsEntity.setAccountType(AccountsConstants.SAVINGS);
        accountsEntity.setBranchAddress(AccountsConstants.ADDRESS);
        accountsEntity.setCreatedAt(LocalDateTime.now());
        accountsEntity.setCreatedBy("Anonymous");
        return accountsEntity;
    }
}
