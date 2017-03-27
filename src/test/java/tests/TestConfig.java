package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import utilities.IOCopier;

public class TestConfig extends Global {
	
	static String base_tests_file;
	static String client_tests_file;
	static Boolean excludedGroupsExist = false;
	static Boolean numSearchesExist = false;
	int newNumSearches = 3;
	
	@Test
	public void configureTests() throws IOException {
		Path dynamic_test = FileSystems.getDefault().getPath("test_suite.yaml");
		Files.deleteIfExists(dynamic_test);
		Path run_tests = FileSystems.getDefault().getPath("run_test_suite.yaml");
		Files.deleteIfExists(run_tests);
		Path run_perf_tests = FileSystems.getDefault().getPath("run_perf_tests.yaml");
		Files.deleteIfExists(run_perf_tests);
		Path perf_test_suite = FileSystems.getDefault().getPath("perf_test_suite.yaml");
		Files.deleteIfExists(perf_test_suite);
		
		InputStream input = new FileInputStream(new File("./resources/client_config.yaml"));
		InputStream client_selectors = new FileInputStream(new File("./resources/client_selectors.yaml"));
		Yaml yaml = new Yaml();
		
		client = System.getProperty("client");
		
		if (System.getProperty("excludeGroup") != null) {
			excludeGroup = System.getProperty("excludeGroup");
			excludedGroupsExist = true;
			System.out.println("Got an exclude group: " + excludeGroup);
		}
		
		if (System.getProperty("numSearches") != null) {
			numSearches = System.getProperty("numSearches");
			numSearchesExist = true;
			newNumSearches = Integer.parseInt(numSearches);
			System.out.println("Got a num search: " + numSearches);
		}
		
		if (System.getProperty("env").isEmpty() || System.getProperty("env") == null) {
			env = "stage";
			//System.out.println("Env1: " + env);
			//System.out.println("Number of searches: " + numSearches);
		}
		else if (System.getProperty("env") != null) {
			env = System.getProperty("env");
			//System.out.println("Env2: " + env);
			//System.out.println("Number of searches: " + numSearches);
		}
		
		//The next group of tests are all reading and grabbing information out of the client_config.yaml file,
		//and executing tests based on parameters passed in during test execution 
		
		//Search client config file for client, create object for that client
		@SuppressWarnings("unchecked")
		Map<String, String> object = (Map<String, String>) yaml.load(input);
		Object val = object.get(client);
		
		@SuppressWarnings("unchecked")
		Map<String, String> cli_select_obj = (Map<String, String>) yaml.load(client_selectors);
		Object cli_selects = cli_select_obj.get(client);
		
		if (val != null && cli_selects != null) {
			//Search client object for env, create env object 
			@SuppressWarnings("unchecked")
			Map<String, String> obj = (Map<String, String>) val;	
			
			Object val1 = obj.get(env);			
			Object url_list = obj.get("urls");
			//Object selectors_list = obj.get("selectors");
			
			@SuppressWarnings("unchecked")
			Map<String, String> obj2 = (Map<String, String>) val;		
			ajax_minicart = obj2.get("ajax_minicart");
			
			@SuppressWarnings("unchecked")
			Map<String, String> obj3 = (Map<String, String>) val;		
			file_extension = obj3.get("file_extension");
			
			@SuppressWarnings("unchecked")
			Map<String, String> obj4 = (Map<String, String>) val;		
			direct_to_checkout = obj4.get("direct_to_checkout");
			
			//grab all client selectors, put them in global variables
			@SuppressWarnings("unchecked")
			Map<String, String> selectors = (Map<String, String>) cli_selects;
			homepage_string = selectors.get("homepage_string");
			homepage_string_selector = selectors.get("homepage_string_selector");		
			simple_product_addToCart = selectors.get("simple_product_addToCart");
			minicart_wrapper = selectors.get("minicart_wrapper");
			minicart_checkout_button = selectors.get("minicart_checkout_button");
			login_email = selectors.get("login_email");
			login_pw = selectors.get("login_pw");
			login_button = selectors.get("login_button");
			acct_login_form = selectors.get("acct_login_form");
			acct_dash = selectors.get("acct_dash");
			acct_dash_name = selectors.get("acct_dash_name");
			cart_page_checkout_button = selectors.get("cart_page_checkout_button");
			continue_as_guest_button = selectors.get("continue_as_guest_button");
			checkout_page_title = selectors.get("checkout_page_title"); 
			billing_form = selectors.get("billing_form");
			billing_fname = selectors.get("billing_fname");
			billing_lname = selectors.get("billing_lname");
			billing_email = selectors.get("billing_email");
			billing_address1 = selectors.get("billing_address1");
			billing_address2 = selectors.get("billing_address2");
			billing_city = selectors.get("billing_city");
			billing_state_select = selectors.get("billing_state_select");
			billing_zip = selectors.get("billing_zip");
			billing_phone = selectors.get("billing_phone");
			billing_continue_button = selectors.get("billing_continue_button");
			header = selectors.get("header");
			search = selectors.get("search");
			homepage_modal = selectors.get("homepage_modal");
			popup_overlay = selectors.get("popup_overlay");
			close_modal = selectors.get("close_modal");
			shipping_method_continue_button = selectors.get("shipping_method_continue_button");
			shipping_method_form = selectors.get("shipping_method_form");
			payment_method_form = selectors.get("payment_method_form");
			cc_payment_option_label = selectors.get("cc_payment_option_label");
			cc_form = selectors.get("cc_form");
			better_cc_form = selectors.get("better_cc_form");
			cc_num = selectors.get("cc_num");
			cc_type = selectors.get("cc_type");
			cc_exp_year = selectors.get("cc_exp_year");
			cc_exp_month = selectors.get("cc_exp_month");
			cc_veri_num = selectors.get("cc_veri_num");
			payment_method_continue_button = selectors.get("payment_method_continue_button");
			submit_order_button = selectors.get("submit_order_button");
			
			if (val1 != null) {
				//Search env object for base url, create object for url
				@SuppressWarnings("unchecked")
				Map<String, String> obj1 = (Map<String, String>) val1;		
				base_url = obj1.get("base_url");
				
				@SuppressWarnings("unchecked")
				Map<String, String> urls = (Map<String, String>) url_list;
				catA_url = urls.get("categoryA_url");
				catB_url = urls.get("categoryB_url");
				simple_product_url = urls.get("simple_product_url");
				cart_url = urls.get("cart_url");
				checkout_url = urls.get("checkout_url");
				
				if (base_url != null) {
					//Search client object for tests, create tests object 
					@SuppressWarnings("unchecked")
					Map<String, String> tests_map = (Map<String, String>) val;	
					Object tests_obj =  tests_map.get("tests");
									
					//Search client object for tests, create tests object 
					@SuppressWarnings("unchecked")
					Map<String, String> tests_map1 = (Map<String, String>) tests_obj;	
					base_tests_file = tests_map1.get("base_tests");
					client_tests_file = tests_map1.get("client_tests");
					
					//Search client object for width / height, aspect ratio object 
					@SuppressWarnings("unchecked")
					Map<String, String> asp_rat_map = (Map<String, String>) val;	
					Object asp_rat_obj =  asp_rat_map.get("desktop");
					
					@SuppressWarnings("unchecked")
					Map<String, String> asp_rat_map1 = (Map<String, String>) asp_rat_obj;
					String str_width = asp_rat_map1.get("width");
					String str_height = asp_rat_map1.get("height");
					desktop_width = Integer.parseInt(str_width);
					desktop_height = Integer.parseInt(str_height);							
					
					InputStream input_base = new FileInputStream(new File("base_tests.yaml"));
					InputStream input_client = new FileInputStream(new File("src/test/java/tests/" + client + "/" + client + "_tests.yaml"));
					
					//Search base tests file for tests, enter each test name into a string array
					Yaml y = new Yaml();
					@SuppressWarnings({ "rawtypes" })
					ArrayList base_list = (ArrayList) y.load(input_base);
					@SuppressWarnings("rawtypes")
					ArrayList client_list = (ArrayList) y.load(input_client);
					
					LinkedHashMap<String, String> tm = new LinkedHashMap<String, String>();
					ArrayList<LinkedHashMap<String, String>> tests_array = new ArrayList<LinkedHashMap<String, String>>();
					
					int ct = base_list.size();
					int ct2 = client_list.size();
					int flag = 0; 
					
					for (int i = 0; i < ct; i++) {
						LinkedHashMap<String, String> x =  (LinkedHashMap<String, String>) base_list.get(i);
						//System.out.println(base_list.get(i));
						
						for (int j = 0; j < ct2; j++) {
							LinkedHashMap<String, String> z =  (LinkedHashMap<String, String>) client_list.get(j);
							//System.out.println(client_list.get(i));
							
							if (x.get("name").equalsIgnoreCase(z.get("name"))) {
								flag = 1;
								//System.out.println("Found a match");
							}
						}
						
						if (flag == 0) {		
							tm.put("name", x.get("name"));
							tm.put("parameters", x.get("parameters"));
							tm.put("classes", x.get("classes"));
							
							//Add in functionality to exclude groups via parameter
							if (excludedGroupsExist) {
								//tm.put("excludedGroups", x.get("excludedGroups"));
								tm.put("excludedGroups", excludeGroup);
							}
							
							tests_array.add(x);
						}
					}
					
					for (int i = 0; i < ct2; i++) {
						LinkedHashMap<String, String> z =  (LinkedHashMap<String, String>) client_list.get(i);
						tm.put("name", z.get("name"));
						tm.put("parameters", z.get("parameters"));
						tm.put("classes", z.get("classes"));					
						
						//Add in functionality to exclude groups via parameter
						if (excludedGroupsExist) {
							//tm.put("excludedGroups", z.get("excludedGroups"));
							tm.put("excludedGroups", excludeGroup);
						}
						
						tests_array.add(z);
					}
					
					FileWriter writer = new FileWriter("test_suite.yaml");
					yaml.dump(tests_array, writer);
					
					//Combine the header file (tests_header.yaml) and dynamically created test suite file (test_suite.yaml)
					IOCopier.joinFiles(new File("run_test_suite.yaml"), new File[] { new File("tests_header.yaml"), new File("test_suite.yaml")});
					
					//update perf test suite if the number of searches is different than the default
					if (numSearchesExist) {
						InputStream perf_input = new FileInputStream(new File("perf_test_tests.yaml"));
						Yaml q = new Yaml();

						@SuppressWarnings("rawtypes")
						ArrayList perf_list = (ArrayList) q.load(perf_input);
						
						//ArrayList<LinkedHashMap<String, String>> perf_array = new ArrayList<LinkedHashMap<String, String>>();
						//ArrayList<HashMap<String, Object>> perf_array = new ArrayList<HashMap<String, Object>>();
						ArrayList<Map<String, Object>> perf_array = new ArrayList<Map<String, Object>>();
						
//						@SuppressWarnings("unchecked")
//						LinkedHashMap<String, Object> z =  (LinkedHashMap<String, Object>) perf_list.get(0);						
//						@SuppressWarnings("unchecked")
//						HashMap<String, Object> w = (HashMap<String, Object>) perf_list.get(0);
						@SuppressWarnings("unchecked")
						Map<String, Object> perf_map = (Map<String, Object>) perf_list.get(0);

						
						for (int i = 0; i < newNumSearches; i++) {										
							perf_map.put("name", "Concurrent search on Chrome for mac " + i);
//							perf_map.replace("name", "Concurrent search on Chrome for mac " + i);
							
							perf_array.add(i, perf_map);
						}
						
						FileWriter writer2 = new FileWriter("perf_test_suite.yaml");
						q.dump(perf_array, writer2);
						
						//Combine the perf test header file with the dynamically created perf test suite
						//IOCopier.joinFiles(new File("run_perf_tests.yaml"), new File[] { new File("perf_test_head.yaml"), new File("perf_test_suite.yaml")});
					}
					else {
						
					}
					
					
					//Combine the perf test header file with the dynamically created perf test suite
					//IOCopier.joinFiles(new File("run_perf_tests.yaml"), new File[] { new File("perf_test_head.yaml"), new File("perf_test_tests.yaml")});
					
					//close all file input streams
					input.close();
					input_base.close();
					input_client.close();
					
					//create new testNG object, add test suite files to it, execute testng suite
					TestNG tng = new TestNG();
					
					//Use this when writing a new test
					//tng.setTestSuites(Arrays.asList("new_test.yaml"));
					
					//Use this when writing a new test and you want to include the performance tests
					//tng.setTestSuites(Arrays.asList("new_test.yaml", "run_perf_tests.yaml"));
					
					//Use this one when you want to run the 2 suites, base and client, separately
					//tng.setTestSuites(Arrays.asList(base_tests_file, "src/test/java/tests/" + client + "/" + client_tests_file));
					
					//Use this when you don't want to run the concurrent search test, but want to run everything else
					//tng.setTestSuites(Arrays.asList("run_test_suite.yaml"));
					
					//Use this one to run full suite
					tng.setTestSuites(Arrays.asList("run_test_suite.yaml", "perf_test.yaml"));
					//tng.run();
				}	
				else {
					System.out.println("The variable 'url' was not found in the client configuration yaml file");
				}
			}
			else {
				System.out.println("Environment " + env + " for client " + client + " not found!");
			}
		}
		else {
			System.out.println("Client code " + client + " not found!");
		}

	}
}
