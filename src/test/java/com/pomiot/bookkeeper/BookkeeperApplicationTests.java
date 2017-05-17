package com.pomiot.bookkeeper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class BookkeeperApplicationTests {

	@Test
	public void contextLoads() {

		Printer printer = new Printer();

		printer.registerObserver(new PrinterObserverImpl());

		printer.setNumber(10000);
	}

	class Printer {

		List<PrinterObserver> observers = new ArrayList<>();

		Integer number = 0;

		public void registerObserver(PrinterObserver observer){
			observers.add(observer);
		}

		public void notifyObservers(){
			observers.forEach(o -> o.update(this));
		}

		public void setNumber(Integer integer){
			this.number = integer;
			notifyObservers();
		}
	}

	interface PrinterObserver {
		void update(Printer printer);
	}

	class PrinterObserverImpl implements PrinterObserver {

		@Override
		public void update(Printer printer) {
			System.out.println("Printer got changed! New number is: " + printer.number);
		}
	}

}
