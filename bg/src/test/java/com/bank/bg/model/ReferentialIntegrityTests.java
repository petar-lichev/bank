package com.bank.bg.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import com.bank.bg.BgApplicationTests;

public class ReferentialIntegrityTests extends BgApplicationTests{
	
	@Test
	public void foreignKeysInAccount() {
		List<String> foreign_keys = new ArrayList<String>();
		foreign_keys.add("USER_ID");
		
		Query query_foreign_keys = em.createNativeQuery("SELECT fkcolumn_name FROM INFORMATION_SCHEMA.CROSS_REFERENCES where fktable_name='ACCOUNT';");
		List<String> lista = query_foreign_keys.getResultList();
		
		boolean flag = !lista.isEmpty();
		
		for(Object o: lista) {
			String foreign_key = (String) o;
			System.out.println(foreign_key);
			flag = foreign_keys.contains(foreign_key);
		}
		
		assertTrue(flag);
	}
	
	//@Test
	public void foreignKeysInUser() {
		
		List<String> foreign_keys = new ArrayList<String>();
		foreign_keys.add("ACCOUNT_ID");
		
		Query query_foreign_keys = em.createNativeQuery("SELECT fkcolumn_name FROM INFORMATION_SCHEMA.CROSS_REFERENCES where fktable_name='USER';");
		List<String> lista = query_foreign_keys.getResultList();
		
		boolean flag = !lista.isEmpty();
		
		
		for(Object o: lista) {
			String foreign_key = (String) o;
			
			flag = foreign_keys.contains(foreign_key);
		}
		
		assertTrue(flag);
	}

}
