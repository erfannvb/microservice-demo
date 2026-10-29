package nvb.dev.accounts.mapper;

import nvb.dev.accounts.dto.AccountsDto;
import nvb.dev.accounts.entity.AccountsEntity;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(AccountsEntity accountsEntity) {
        return new AccountsDto(
                accountsEntity.getAccountNumber(),
                accountsEntity.getAccountType(),
                accountsEntity.getBranchAddress()
        );
    }

    public static AccountsEntity mapToAccountsEntity(AccountsDto accountsDto) {
        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setAccountNumber(accountsDto.accountNumber());
        accountsEntity.setAccountType(accountsDto.accountType());
        accountsEntity.setBranchAddress(accountsDto.branchAddress());
        return accountsEntity;
    }

}
