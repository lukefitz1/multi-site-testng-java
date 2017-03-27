package tests;

public class Global {

	protected static String env;
	protected static String client;
	protected static String excludeGroup;
	protected static String numSearches;
	protected static String base_url;
	protected static String catA_url;
	protected static String catB_url;
	protected static String simple_product_url;
	protected static String cart_url;
	protected static String checkout_url;
	protected static String ajax_minicart;
	protected static String file_extension;
	protected static String direct_to_checkout;
	protected static String homepage_string;
	protected static String homepage_string_selector;
	protected static String login_email;
	protected static String login_pw;
	protected static String login_button;
	protected static String acct_dash;
	protected static String acct_dash_name;
	protected static String acct_login_form;
	protected static String simple_product_addToCart;
	protected static String minicart_wrapper;
	protected static String minicart_checkout_button;
	protected static int desktop_width;
	protected static int desktop_height;
	protected static String cart_page_checkout_button;
	protected static String continue_as_guest_button;
	protected static String checkout_page_title;
	protected static String billing_form;
	protected static String billing_fname;
	protected static String billing_lname;
	protected static String billing_email;
	protected static String billing_address1;
	protected static String billing_address2;
	protected static String billing_city;
	protected static String billing_state_select;
	protected static String billing_zip;
	protected static String billing_phone;
	protected static String billing_continue_button;
	protected static String header;
	protected static String search;
	protected static String homepage_modal;
	protected static String popup_overlay;
	protected static String close_modal;
	protected static String shipping_method_continue_button;
	protected static String shipping_method_form;
	protected static String payment_method_form;
	protected static String cc_payment_option_label;
	protected static String cc_form;
	protected static String better_cc_form;
	protected static String cc_num;
	protected static String cc_type;
	protected static String cc_exp_year;
	protected static String cc_exp_month;
	protected static String cc_veri_num;
	protected static String payment_method_continue_button;
	protected static String submit_order_button;
	
	public static String getEnv() {
		return env;
	}
	
	public static String getClient() {
		return client;
	}
	
	public static String getBaseUrl() {
		return base_url;
	}
	
	public static String getCatAUrl() {
		return catA_url;
	}
	
	public static String getCatBUrl() {
		return catB_url;
	}
	
	public static String getSimpleProductUrl() {
		return simple_product_url;
	}
	
	public static String getCartUrl() {
		return cart_url;
	}
	
	public static String getCheckoutUrl() {
		return checkout_url;
	}
	
	public static Boolean getAjaxMiniCart() {
		if (ajax_minicart.equalsIgnoreCase("true")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static Boolean getFileExtension() {
		if (file_extension.equalsIgnoreCase("true")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static Boolean getDirectToCheckout() {
		if (direct_to_checkout.equalsIgnoreCase("true")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static String getHomepageString() {
		return homepage_string;
	}
	
	public static String getHomepageStringSelector() {
		return homepage_string_selector;
	}
	
	public static String getLoginEmail() {
		return login_email;
	}
	
	public static String getLoginPw() {
		return login_pw;
	}
	
	public static String getLoginButton() {
		return login_button;
	}
	
	public static String getAcctDash() {
		return acct_dash;
	}
	
	public static String getAcctDashName() {
		return acct_dash_name;
	}
	
	public static String getAcctLoginForm() {
		return acct_login_form;
	}
	
	public static String getSimpleProductAddToCartButton() {
		return simple_product_addToCart;
	}
	
	public static String getMiniCartWrapper() {
		return minicart_wrapper;
	}
	
	public static String getMiniCartCheckoutButton() {
		return minicart_checkout_button;
	}
	
	public static int getDesktopWidth() {
		return desktop_width;
	}
	
	public static int getDesktopHeight() {
		return desktop_height;
	}
	
	public static String getCartPageCheckoutButton() {
		return cart_page_checkout_button;
	}

	public static String getContinueAsGuestButton() {
		return continue_as_guest_button;
	}
	
	public static String getCheckoutPageTitle() {
		return checkout_page_title;
	}
	
	public static String getBillingForm() {
		return billing_form;
	}
	
	public static String getBillingFName() {
		return billing_fname;
	}
	
	public static String getBillingLName() {
		return billing_lname;
	}
	
	public static String getBillingEmail() {
		return billing_email;
	}
	
	public static String getBillingAddress1() {
		return billing_address1;
	}
	
	public static String getBillingAddress2() {
		return billing_address2;
	}
	
	public static String getBillingCity() {
		return billing_city;
	}
	
	public static String getBillingStateSelect() {
		return billing_state_select;
	}
	
	public static String getBillingZip() {
		return billing_zip;
	}
	
	public static String getBillingPhone() {
		return billing_phone;
	} 
	
	public static String getBillingContinueButton() {
		return billing_continue_button;
	}
	
	public static String getHeader() {
		return header;
	}
	
	public static String getSearch() {
		return search;
	}
	
	public static Boolean getHomepageModal() {
		if (homepage_modal.equalsIgnoreCase("true")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static String getPopupOverlay() {
		return popup_overlay;
	} 
	
	public static String getCloseModal() {
		return close_modal;
	}
	
	public static String getShippingMethodContinueButton() {
		return shipping_method_continue_button;
	}
	
	public static String getShippingMethodsForm() {
		return shipping_method_form;
	}
	
	public static String getPaymentMethodsForm() {
		return payment_method_form;
	}
	
	public static String getCCPaymentOptionLabel() {
		return cc_payment_option_label;
	}
	
	public static String getCCForm() {
		return cc_form;
	}
	
	public static Boolean getBetterCCForm() {
		if (better_cc_form.equalsIgnoreCase("true")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static String getCCNum() {
		return cc_num;
	}
	
	public static String getCCType() {
		return cc_type;
	}
	
	public static String getCCExpMonth() {
		return cc_exp_month;
	}
	
	public static String getCCExpYear() {
		return cc_exp_year;
	}
	
	public static String getCCVeriNum() {
		return cc_veri_num;
	}
	
	public static String getPaymentMethodContinueButton() {
		return payment_method_continue_button;
	}
	
	public static String getSubmitOrderButton() {
		return submit_order_button;
	}
}
