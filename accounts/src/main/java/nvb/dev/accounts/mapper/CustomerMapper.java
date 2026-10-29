package nvb.dev.accounts.mapper;

import nvb.dev.accounts.dto.AccountsDto;
import nvb.dev.accounts.dto.CustomerDto;
import nvb.dev.accounts.entity.CustomerEntity;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(CustomerEntity customerEntity, AccountsDto accountsDto) {
        return new CustomerDto(
                customerEntity.getName(),
                customerEntity.getEmail(),
                customerEntity.getMobileNumber(),
                accountsDto
        );
    }

    public static CustomerEntity mapToCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDto.name());
        customerEntity.setEmail(customerDto.email());
        customerEntity.setMobileNumber(customerDto.mobileNumber());
        return customerEntity;
    }

}
