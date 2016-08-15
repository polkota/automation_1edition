package ua.qa.edusson.tests;

public class TestStandartCheckEdubirdieProduction {
	public String orderUrl;
	public String orderId;
	public String writerUrl;
	
	public String customerReleasedPercent;
	public String writerReleasedPercent;
/*	// ������������� �������
			UserAuthorizationPage userAuthorizationPage = new UserAuthorizationPage();
			MyOrdersCustomerPage myOrdersCustomerPage = new MyOrdersCustomerPage();
			OrderCreateCustomerPage orderCreateCustomerPage = new OrderCreateCustomerPage();
			OrderBiddingCustomerPage orderBiddingCustomerPage = new OrderBiddingCustomerPage();
			OrderBiddingWriterPage orderBiddingWriterPage = new OrderBiddingWriterPage();
			HeaderMenu headerMenu = new HeaderMenu();
			MyOrdersWriterPage myOrdersWriterPage = new MyOrdersWriterPage();
			OrderPayCustomerPage orderPayCustomerPage = new OrderPayCustomerPage();
			PayPalPage payPalPage = new PayPalPage();
			//OrderPayThankYouCustomerPage orderPayThankYouCustomerPage = new OrderPayThankYouCustomerPage();
			OrderInProgressPage orderInProgressPage = new OrderInProgressPage();
			OrderFinishedViewPage orderFinishedViewPage = new OrderFinishedViewPage();
			CreditCardPayment creditCardPayment = new CreditCardPayment();
	
	
	@Before
	public void setUp() throws Exception {
		Helper.driverSetUp();
	}
	
    @Test
	// � ������� �� ������� 0, ������ ������ ����� PayPall, ����� ��������
	// 20%+80%
	
	public void standartCheck_PAyPal_Production_Edubirdie() throws Exception {
    	Helper.driver.get("http://edubirdie.com/");
		// ��������� ��������
		userAuthorizationPage.logIn(Config.customer1, Config.password);
		Helper.sleep(1);
		//go to order form
	    myOrdersCustomerPage.makeNewOrder();
		// create order
	    Helper.sleep(1);
		orderCreateCustomerPage.createOrder("test for webdriver", "test");
		//assertTrue(Helper.driver.getCurrentUrl().contains("order#redirect_url="));
		Helper.sleep(1);
		//������� �������� ����� �������� ������
		Helper.driver.navigate().refresh();
		// ��������� ��� �������� �������� ������ � ����������
	    orderUrl = Helper.driver.getCurrentUrl();
	    orderId = orderUrl.substring(32);
	    System.out.println(orderId);
	    writerUrl = "http://edusson.com/order/view/"+orderId;
		Helper.sleep(1);
		Helper.goToEdusson();
		Helper.sleep(1);
		// ��������� ���������
		userAuthorizationPage.logIn(Config.writer1, Config.password);
		//��������� ���������� �����
		Helper.sleep(2);
		myOrdersWriterPage.closePopup();
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(writerUrl);
		Helper.sleep(2);
		System.out.println(Helper.driver.getCurrentUrl());
		// ������� ���
		orderBiddingWriterPage.createBid("6"); 
		Helper.sleep(2);
		//Helper.goToEdubirdie();
		//Helper.sleep(1);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		Helper.sleep(2);
		// �������� ��� ������� ��������
		orderBiddingCustomerPage.bid1();
		Helper.sleep(2);
		// ����������� ���, ��������� �� �������� ������
		orderPayCustomerPage.choosePayPal();
		Helper.sleep(2);
		orderPayCustomerPage.clickReserveButton();
		//������������� �� frame �� �������� �������
		//Helper.sleep(1);
		//Helper.driver.switchTo().frame(Helper.driver.findElement(By.name("injectedUl")));
		Helper.sleep(1);
		// ��������� � PayPall � ����������� ������
		payPalPage.confirmPayPal(Config.paypall_login, Config.paypall_pass);
		//payPalPage.clickContinue();
		// ���� ����������� �� ����
		//Helper.sleep(30);
		//payPalPage.confirmPayPal_2(Config.paypall_login, Config.paypall_pass);
		Helper.sleep(2);
		//Helper.goToEdusson();
		//Helper.sleep(1);
		//����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(writerUrl);
	    //��������� �������
		orderInProgressPage.uploadRevision();
		Helper.sleep(2);
		//Helper.goToEdubirdie();
		//Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
	    // ������� �������� 10%
	    orderInProgressPage.releaseMoney("20");
	    // �������� �������� % ���������� ����� �� �������� �������
	    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
	   // Helper.goToEdusson();
		//Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(writerUrl);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		//Helper.goToEdubirdie();
		//Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(orderUrl);
		// ������� �������� 90%
		orderInProgressPage.releaseMoney("80");
		Helper.sleep(2);
		//orderFinishedViewPage.closePopup();
		// �������� �������� % ���������� ����� �� �������� �������
		customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
		assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
		Helper.sleep(2);
		//Helper.goToEdusson();
		//Helper.sleep(2);
		// ����� ��� �������� ������ �� ���������� � ��������� �� ����
		Helper.driver.get(writerUrl);
		// �������� �������� % ���������� ����� �� �������� ��������
		writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
		// ���������� �������� ���������� ����� � ������� � � ��������
		assertEquals(customerReleasedPercent, writerReleasedPercent);
		//��������� ������� ������ order finished 
		assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
		Helper.sleep(2);
		//headerMenu.userLogOut();
		System.out.println("TEST PASSED");

	}

		@After
	public void theEnd(){
			Helper.quit();
				
		}
		 @Test
			// � ������� �� ������� 0, ������ ������ ����� PayPall, ����� ��������
			// 20%+80%
			
			public void standartCheck_CreditCard_Production_Edubirdie() throws Exception {
			    Helper.driver.get("http://edubirdie.com/");
				// ��������� ��������
				userAuthorizationPage.logIn(Config.customer1, Config.password);
				Helper.sleep(1);
				//go to order form
			    myOrdersCustomerPage.makeNewOrder();
				// create order
			    Helper.sleep(1);
				orderCreateCustomerPage.createOrder("test for webdriver", "test");
				//assertTrue(Helper.driver.getCurrentUrl().contains("order#redirect_url="));
				Helper.sleep(1);
				//������� �������� ����� �������� ������
				Helper.driver.navigate().refresh();
				// ��������� ��� �������� �������� ������ � ����������
			    orderUrl = Helper.driver.getCurrentUrl();
			    orderId = orderUrl.substring(32);
			    System.out.println(orderId);
			    writerUrl = "http://edusson.com/order/view/"+orderId;
				Helper.sleep(1);
				Helper.goToEdusson();
				Helper.sleep(1);
				// ��������� ���������
				userAuthorizationPage.logIn(Config.writer1, Config.password);
				//��������� ���������� �����
				Helper.sleep(2);
				myOrdersWriterPage.closePopup();
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(writerUrl);
				Helper.sleep(2);
				System.out.println(Helper.driver.getCurrentUrl());
				// ������� ���
				orderBiddingWriterPage.createBid("6"); 
				Helper.sleep(2);
				//Helper.goToEdubirdie();
				//Helper.sleep(1);
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(orderUrl);
				Helper.sleep(2);
				// �������� ��� ������� ��������
				orderBiddingCustomerPage.bid1();
				Helper.sleep(2);
				// ����������� ���, ��������� �� �������� ������
				orderPayCustomerPage.chooseCardPay();
				Helper.sleep(2);
				orderPayCustomerPage.clickReserveButton();
				creditCardPayment.setAllFields();
				Helper.sleep(60);
				//Helper.goToEdusson();
				//Helper.sleep(1);
				//����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(writerUrl);
			    //��������� �������
				orderInProgressPage.uploadRevision();
				Helper.sleep(2);
				//Helper.goToEdubirdie();
				//Helper.sleep(2);
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(orderUrl);
			    // ������� �������� 10%
			    orderInProgressPage.releaseMoney("20");
			    // �������� �������� % ���������� ����� �� �������� �������
			    customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
			   // Helper.goToEdusson();
				//Helper.sleep(2);
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(writerUrl);
				// �������� �������� % ���������� ����� �� �������� ��������
				writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
				// ���������� �������� ���������� ����� � ������� � � ��������
				assertEquals(customerReleasedPercent, writerReleasedPercent);
				//Helper.goToEdubirdie();
				//Helper.sleep(2);
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(orderUrl);
				// ������� �������� 90%
				orderInProgressPage.releaseMoney("80");
				Helper.sleep(2);
				//orderFinishedViewPage.closePopup();
				// �������� �������� % ���������� ����� �� �������� �������
				customerReleasedPercent = orderInProgressPage.checkReleasedMoneyCustomerPage();
				assertTrue(orderFinishedViewPage.checkCustomerPageFinishedText());
				Helper.sleep(2);
				//Helper.goToEdusson();
				//Helper.sleep(2);
				// ����� ��� �������� ������ �� ���������� � ��������� �� ����
				Helper.driver.get(writerUrl);
				// �������� �������� % ���������� ����� �� �������� ��������
				writerReleasedPercent = orderInProgressPage.checkReleasedMoneyWriterPage();
				// ���������� �������� ���������� ����� � ������� � � ��������
				assertEquals(customerReleasedPercent, writerReleasedPercent);
				//��������� ������� ������ order finished 
				assertTrue(orderFinishedViewPage.checkWriterPageFinishedText());
				Helper.sleep(2);
				//headerMenu.userLogOut();
				System.out.println("TEST PASSED");

			}*/
	}

