package com.caju.transaction.domain.account

import com.caju.transaction.domain.Identifier

data class AccountId(override val value: String) : Identifier {

}