# springpostal
Java based Spring library wrapper for libpostalrest address services


##usage
This library is released via **jitpack.io**

1. Add it in your root build.gradle at the end of repositories:
            
        allprojects {<br>	
            repositories {<br>
                ...<br>
                maven { url 'https://jitpack.io' }
            }
        }


2. Add the dependency
   
    	dependencies {
    	        implementation 'cc.dividebyzero:springpostal:1.0.0'
    	}
    
3. Add the postal host to your application.properties
    `cc.dividebyzero.springpostal.baseUrl=${POSTAL_BASE_URL:localhost:8080}` 

4. Inject PostalService in your code and use it

        @Autowired
        PostalService postalService;
        
        void foo(){
            PostalAddress address = postalService.getStructuredAddress("Mörfelder Landstraße 362, 60528 Frankfurt am Main");
        } 