package DataProviders;

import org.testng.annotations.DataProvider;
import readers.JsonReader;

public class TestDataProviders {
    // BaseTests
    @DataProvider(name = "validUserLoginData")
    public static Object[][] getUserLoginData() {
        JsonReader reader = new JsonReader("Login");
        return new Object[][] {
            {
                reader.getJsonData("valid.username", ""),
                reader.getJsonData("valid.password", "")
            }
        };
    }
    
    @DataProvider(name = "adminLoginData")
    public static Object[][] getAdminLoginData() {
        JsonReader reader = new JsonReader("Login");
        return new Object[][] {
            {
                reader.getJsonData("admin.username", ""),
                reader.getJsonData("admin.password", "")
            }
        };
    }

    @DataProvider(name = "invalidUserLoginData")
    public static Object[][] getInvalidUserLoginData() {
        JsonReader reader = new JsonReader("Login");
        return new Object[][] {
            {
                reader.getJsonData("invaliduser.username", ""),
                reader.getJsonData("invaliduser.password", ""),
                reader.getJsonData("invaliduser.ExpectedError", "")
            }
        };
    }

    @DataProvider(name = "invalidpassLoginData")
    public static Object[][] getInvalidPassLoginData() {
        JsonReader reader = new JsonReader("Login");
        return new Object[][] {
            {
                reader.getJsonData("invalidpass.username", ""),
                reader.getJsonData("invalidpass.password", ""),
                reader.getJsonData("invalidpass.ExpectedError", "")
            }
        };
    }

    @DataProvider(name = "emptyPassLoginData")
    public static Object[][] getEmptyPassLoginData() {
        JsonReader reader = new JsonReader("Login");
        return new Object[][] {
            {
                reader.getJsonData("emptyPass.username", ""),
                reader.getJsonData("emptyPass.password", ""),
                reader.getJsonData("emptyPass.ExpectedError", "")
            }
        };
    }

    @DataProvider(name = "emptyMailLoginData")
    public static Object[][] getEmptyMailLoginData() {
        JsonReader reader = new JsonReader("Login");
        return new Object[][] {
            {
                reader.getJsonData("emptyMail.username", ""),
                reader.getJsonData("emptyMail.password", ""),
                reader.getJsonData("emptyMail.ExpectedError", "")
            }
        };
    }

    @DataProvider(name = "noatinemailLoginData")
    public static Object[][] getNoatinemailLoginData() {
        JsonReader reader = new JsonReader("Login");
        return new Object[][] {
            {
                reader.getJsonData("noatinemail.username", ""),
                reader.getJsonData("noatinemail.password", ""),
                reader.getJsonData("noatinemail.ExpectedError", "")
            }
        };
    }

    // RegistrationTest
    @DataProvider(name = "validRegistrationData")
    public static Object[][] getValidRegistrationData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("valid.name", ""),
                reader.getJsonData("valid.email", ""),
                reader.getJsonData("valid.password", ""),
                reader.getJsonData("valid.phone", ""),
                reader.getJsonData("valid.address", "")
            }
        };
    }
    
    @DataProvider(name = "shortPasswordData")
    public static Object[][] getShortPasswordData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("shortPassword.name", ""),
                reader.getJsonData("shortPassword.email", ""),
                reader.getJsonData("shortPassword.password", ""),
                reader.getJsonData("shortPassword.phone", ""),
                reader.getJsonData("shortPassword.address", ""),
                reader.getJsonData("shortPassword.expectedMessage", "")
            }
        };
    }

    @DataProvider(name = "emptyPasswordData")
    public static Object[][] getEmptyPasswordData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("emptyPassword.name", ""),
                reader.getJsonData("emptyPassword.email", ""),
                reader.getJsonData("emptyPassword.password", ""),
                reader.getJsonData("emptyPassword.phone", ""),
                reader.getJsonData("emptyPassword.address", ""),
                reader.getJsonData("emptyPassword.expectedMessage", "")
            }
        };
    }
    
    @DataProvider(name = "passwordWithoutNumberData")
    public static Object[][] getPasswordWithoutNumberData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("passwordWithoutNumber.name", ""),
                reader.getJsonData("passwordWithoutNumber.email", ""),
                reader.getJsonData("passwordWithoutNumber.password", ""),
                reader.getJsonData("passwordWithoutNumber.phone", ""),
                reader.getJsonData("passwordWithoutNumber.address", ""),
                reader.getJsonData("passwordWithoutNumber.expectedMessage", "")
            }
        };
    }
    
    @DataProvider(name = "emptyNameData")
    public static Object[][] getEmptyNameData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("emptyName.name", ""),
                reader.getJsonData("emptyName.email", ""),
                reader.getJsonData("emptyName.password", ""),
                reader.getJsonData("emptyName.phone", ""),
                reader.getJsonData("emptyName.address", ""),
                reader.getJsonData("emptyName.expectedMessage", "")
            }
        };
    }

    @DataProvider(name = "shortNameData")
    public static Object[][] getShortNameData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("shortName.name", ""),
                reader.getJsonData("shortName.email", ""),
                reader.getJsonData("shortName.password", ""),
                reader.getJsonData("shortName.phone", ""),
                reader.getJsonData("shortName.address", ""),
                reader.getJsonData("shortName.expectedMessage", "")
            }
        };
    }
    
    @DataProvider(name = "existingEmailData")
    public static Object[][] getExistingEmailData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("existingEmail.name", ""),
                reader.getJsonData("existingEmail.email", ""),
                reader.getJsonData("existingEmail.password", ""),
                reader.getJsonData("existingEmail.phone", ""),
                reader.getJsonData("existingEmail.address", ""),
                reader.getJsonData("existingEmail.expectedMessage", "")
            }
        };
    }

    @DataProvider(name = "noAtEmailData")
    public static Object[][] getNoAtEmailData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("noAtEmail.name", ""),
                reader.getJsonData("noAtEmail.email", ""),
                reader.getJsonData("noAtEmail.password", ""),
                reader.getJsonData("noAtEmail.phone", ""),
                reader.getJsonData("noAtEmail.address", ""),
                reader.getJsonData("noAtEmail.expectedMessage", "")
            }
        };
    }

    @DataProvider(name = "symbolsNotAtEmailData")
    public static Object[][] getSymbolsNotAtEmailData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("symbolsNotAtEmail.name", ""),
                reader.getJsonData("symbolsNotAtEmail.email", ""),
                reader.getJsonData("symbolsNotAtEmail.password", ""),
                reader.getJsonData("symbolsNotAtEmail.phone", ""),
                reader.getJsonData("symbolsNotAtEmail.address", ""),
                reader.getJsonData("symbolsNotAtEmail.expectedMessage", "")
            }
        };
    }

    @DataProvider(name = "emptyPhoneData")
    public static Object[][] getEmptyPhoneData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("emptyPhone.name", ""),
                reader.getJsonData("emptyPhone.email", ""),
                reader.getJsonData("emptyPhone.password", ""),
                reader.getJsonData("emptyPhone.phone", ""),
                reader.getJsonData("emptyPhone.address", ""),
                reader.getJsonData("emptyPhone.expectedMessage", "")
            }
        };
    }

    @DataProvider(name = "shortPhoneData")
    public static Object[][] getShortPhoneData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("shortPhone.name", ""),
                reader.getJsonData("shortPhone.email", ""),
                reader.getJsonData("shortPhone.password", ""),
                reader.getJsonData("shortPhone.phone", ""),
                reader.getJsonData("shortPhone.address", ""),
                reader.getJsonData("shortPhone.expectedMessage", "")
            }
        };
    }

    @DataProvider(name = "strangeSymbolPhoneData")
    public static Object[][] getStrangeSymbolPhoneData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("strangeSymbolPhone.name", ""),
                reader.getJsonData("strangeSymbolPhone.email", ""),
                reader.getJsonData("strangeSymbolPhone.password", ""),
                reader.getJsonData("strangeSymbolPhone.phone", ""),
                reader.getJsonData("strangeSymbolPhone.address", ""),
                reader.getJsonData("strangeSymbolPhone.expectedMessage", "")
            }
        };
    }

    @DataProvider(name = "emptyAddressData")
    public static Object[][] getEmptyAddressData() {
        JsonReader reader = new JsonReader("Register");
        return new Object[][] {
            {
                reader.getJsonData("emptyAddress.name", ""),
                reader.getJsonData("emptyAddress.email", ""),
                reader.getJsonData("emptyAddress.password", ""),
                reader.getJsonData("emptyAddress.phone", ""),
                reader.getJsonData("emptyAddress.address", ""),
                reader.getJsonData("emptyAddress.expectedMessage", "")
            }
        };
    }

    // CartTest

    @DataProvider(name = "clearCartData")
    public static Object[][] getCartDataForClearCart() {
        JsonReader reader = new JsonReader("Products");
        return new Object[][] {
            {
                reader.getJsonData("cartAlert.clearCartSuccess", "")
            }
        };
    }

    @DataProvider(name = "cartQuantityData")
    public static Object[][] getCartQuantityData() {
        JsonReader reader = new JsonReader("Products");
        return new Object[][] {
            {
            reader.getJsonData("product1.Name", ""),
            reader.getJsonData("product1.Price", ""),
            reader.getJsonData("product1.PriceDoubled", ""),
            reader.getJsonData("product1.PriceTriple", ""),
            reader.getJsonData("productsAlert.addToCartSuccess", "")
            }
        };
    }

    @DataProvider(name = "totalPriceData")
    public static Object[][] getTotalPriceData() {
        JsonReader reader = new JsonReader("Products");
        return new Object[][] {
            { reader.getJsonData("SumOfFirst3Products.Price", "") }
        };
    }

    //addtocart tests

    @DataProvider(name = "addToCartData")
    public static Object[][] getAddToCartData() {
        JsonReader reader = new JsonReader("Products");
        return new Object[][] {
            {
                reader.getJsonData("product1.Name", ""),
                reader.getJsonData("product1.Price", ""),
                reader.getJsonData("productsAlert.addToCartSuccess", "")
            }
        };
    }

    @DataProvider(name = "addToCartGuestData")
    public static Object[][] getAddToCartGuestData() {
        JsonReader reader = new JsonReader("Products");
        return new Object[][] {
                {
                        reader.getJsonData("product1.Name", ""),
                        reader.getJsonData("product1.Price", ""),
                        reader.getJsonData("productsAlert.addToCartGuest", "")
                }
        };
    }

    //categories tests
    @DataProvider(name = "elecCategoryData")
    public static Object[][] getElecCategoryData() {
        JsonReader reader = new JsonReader("Category");
        return new Object[][] {
            {
                reader.getJsonData("categories.elec", ""),
                reader.getJsonData("categories.elecProduct", "")
            }
        };
    }

    @DataProvider(name = "clothCategoryData")
    public static Object[][] getClothCategoryData() {
        JsonReader reader = new JsonReader("Category");
        return new Object[][] {
            {
                reader.getJsonData("categories.cloth", ""),
                reader.getJsonData("categories.clothProduct", "")
            }
        };
    }

    @DataProvider(name = "bookCategoryData")
    public static Object[][] getBookCategoryData() {
        JsonReader reader = new JsonReader("Category");
        return new Object[][] {
            {
                reader.getJsonData("categories.Books", ""),
                reader.getJsonData("categories.bookProduct", "")
            }
        };
    }


   @DataProvider(name = "homeGardenCategoryData")
    public static Object[][] getHomeGardenCategoryData() {
        JsonReader reader = new JsonReader("Category");
        return new Object[][] {
            {
                reader.getJsonData("categories.home", ""),
                reader.getJsonData("categories.containHome", "")
            }
        };
    }


    @DataProvider(name = "accessoriesCategoryData")
    public static Object[][] getAccessoriesCategoryData() {
        JsonReader reader = new JsonReader("Category");
        return new Object[][] {
            {
                reader.getJsonData("categories.Accessories", ""),
                reader.getJsonData("categories.accessoriesNoProducts", "")
            }
        };
    }

    //header tests
    @DataProvider(name = "homeHeaderData")
    public static Object[][] getHomeHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.home", "")
            }
        };
    }

    @DataProvider(name = "productsHeaderData")
    public static Object[][] getProductsHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.products", "")
            }
        };
    }

    @DataProvider(name = "cartHeaderData")
    public static Object[][] getCartHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.cart", "")
            }
        };
    }

    @DataProvider(name = "ordersHeaderData")
    public static Object[][] getOrdersHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.orders", "")
            }
        };
    }

    @DataProvider(name = "helpHeaderData")
    public static Object[][] getHelpHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.help", "")
            }
        };
    }

    @DataProvider(name = "contactHeaderData")
    public static Object[][] getContactHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.contact", "")
            }
        };
    }

    @DataProvider(name = "advancedSearchHeaderData")
    public static Object[][] getAdvancedSearchHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.advancedSearch", "")
            }
        };
    }

    @DataProvider(name = "loginHeaderData")
    public static Object[][] getLoginHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.login", "")
            }
        };
    }

    @DataProvider(name = "registerHeaderData")
    public static Object[][] getRegisterHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.register", "")
            }
        };
    }

    @DataProvider(name = "logoutHeaderData")
    public static Object[][] getLogoutHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.logout", "")
            }
        };
    }

    @DataProvider(name = "notificationsHeaderData")
    public static Object[][] getNotificationsHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.notifications", "")
            }
        };
    }

    @DataProvider(name = "profileHeaderData")
    public static Object[][] getProfileHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.profile", "")
            }
        };
    }

    @DataProvider(name = "wishlistHeaderData")
    public static Object[][] getWishlistHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.wishlist", "")
            }
        };
    }

    @DataProvider(name = "adminHeaderData")
    public static Object[][] getAdminHeaderData() {
        JsonReader reader = new JsonReader("Headers");
        return new Object[][] {
            {
                reader.getJsonData("headers.admin", "")
            }
        };
    }

    //ordersTests
    @DataProvider(name = "ordersData")
    public static Object[][] getOrdersData() {
        JsonReader reader = new JsonReader("Orders");
        return new Object[][] {
            {
                reader.getJsonData("product1.Name", ""),
                reader.getJsonData("productsAlert.addToCartSuccess", ""),
                reader.getJsonData("cartAlert.orderSuccess", ""),
                reader.getJsonData("headers.cart", "")
            }
        };
    }

    //productSearchTests
    @DataProvider(name = "validSearchData1")
    public static Object[][] getValidSearchData() {
        JsonReader reader = new JsonReader("Search");
        return new Object[][] {
            {
                reader.getJsonData("ValidSearch1.Search", ""),
                reader.getJsonData("ValidSearch1.Category", ""),
                reader.getJsonData("ValidSearch1.MinPrice", ""),
                reader.getJsonData("ValidSearch1.MaxPrice", "")
            }
        };
    }
    @DataProvider(name = "validSearchData2")
    public static Object[][] getValidSearchData2() {
        JsonReader reader = new JsonReader("Search");
        return new Object[][] {
            {
                reader.getJsonData("ValidSearch2.Search", ""),
                reader.getJsonData("ValidSearch2.Category", ""),
                reader.getJsonData("ValidSearch2.MinPrice", ""),
                reader.getJsonData("ValidSearch2.MaxPrice", "")
            }
        };
    }
    @DataProvider(name = "validSearchData3")
    public static Object[][] getValidSearchData3() {
        JsonReader reader = new JsonReader("Search");
        return new Object[][] {
            {
                reader.getJsonData("ValidSearch3.Search", ""),
                reader.getJsonData("ValidSearch3.Category", ""),
                reader.getJsonData("ValidSearch3.MinPrice", ""),
                reader.getJsonData("ValidSearch3.MaxPrice", "")
            }
        };
    }

    @DataProvider(name = "validSearchData4")
    public static Object[][] getValidSearchData4() {
        JsonReader reader = new JsonReader("Search");
        return new Object[][] {
            {
                reader.getJsonData("ValidSearch4.Search", ""),
                reader.getJsonData("ValidSearch4.Category", ""),
                reader.getJsonData("ValidSearch4.MinPrice", ""),
                reader.getJsonData("ValidSearch4.MaxPrice", "")
            }
        };
    } 


    @DataProvider(name = "invalidSearchData1")
    public static Object[][] getInvalidSearchData1() {
        JsonReader reader = new JsonReader("Search");
        return new Object[][] {
            {
                reader.getJsonData("InvalidSearch1.Search", ""),
                reader.getJsonData("InvalidSearch1.Category", ""),
                reader.getJsonData("InvalidSearch1.MinPrice", ""),
                reader.getJsonData("InvalidSearch1.MaxPrice", "")
            }
        };
    } 

    @DataProvider(name = "invalidSearchData2")
    public static Object[][] getInvalidSearchData2() {
        JsonReader reader = new JsonReader("Search");
        return new Object[][] {
            {
                reader.getJsonData("InvalidSearch2.Search", ""),
                reader.getJsonData("InvalidSearch2.Category", ""),
                reader.getJsonData("InvalidSearch2.MinPrice", ""),
                reader.getJsonData("InvalidSearch2.MaxPrice", "")
            }
        };
    } 



}
