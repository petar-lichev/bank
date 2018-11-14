package com.bank.bg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bank.bg.model.AccountTests;
import com.bank.bg.model.UserTests;

@RunWith(Suite.class)
@SuiteClasses({AccountTests.class, UserTests.class})
public class AllTests {

}
