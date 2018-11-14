package com.bank.bg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bank.bg.model.AccountTests;
import com.bank.bg.model.ReferentialIntegrityTests;
import com.bank.bg.model.UserTests;
import com.bank.bg.service.UserRepositoryTest;

@RunWith(Suite.class)
@SuiteClasses({AccountTests.class, UserTests.class, UserRepositoryTest.class, ReferentialIntegrityTests.class})
public class AllTests {

}
