const app = Vue.createApp({
    el: "#app",
    data() {
        return {
            screen: 1,

            result: "result",
            count: 1,
            currentMeal: "",
            currentMealRecipes: "",
            currentMealImage: "http://localhost:8080/meals/Default.png",
            currentMealRestaurants: "",

            userFavourites: null,
            useFavs: "",

            counting: 0,

            currentRecipes: "",
            recipeText: "",

            newMealName: "",
            newMealImgPath: "",

            firstMeal: "",
            firstMealImage: "http://localhost:8080/",
            foodName: 'testName',
            foodImage: '../images/meals/lena.jpg',
            foodDescription: 'This is a food description',

            recipeTitle1: 'recipe 1 is here',
            recipeTitle2: 'recipe 2 is here',
            recipeTitle3: 'recipe 3 is here',

            chosenRecipeTitle: 'chosen recipe title is here',
            chosenRecipeContent: 'chosen recipe content is here',

            restaurantName1: 'restaurant 1 is here',
            restaurantName2: 'restaurant 2 is here',
            restaurantName3: 'restaurant 3 is here',

            chosenRestaurantName: 'chosen restaurant name is here',
            chosenRestaurantAddress: 'chosen restaurant address is here',
            chosenRestaurantWebsite: 'chosen restaurant website is here',

            hunterUsername: 'damon',
            hunterPassword: '1204578616',
            accessKey: "",

            hunterUsernameIn: '',
            hunterPasswordIn: '',
            hunterAddressIn: '',
            hunterPostcodeIn: '',

            restaurantUsername: 'damon',
            restaurantPassword: '12345678',

            restaurantUsernameIn: '',
            restaurantPasswordIn: '',
            restaurantDescriptionIn: '',
            restaurantAddressIn: '',
            restaurantPostcodeIn: '',

            mealOptions: [{meal: 'granola'}],

            adminUsername: 'damon',
            adminPassword: '1204578616',

            applicationName1: 'restaurant application 1',
            applicationName2: 'restaurant application 2',
            applicationName3: 'restaurant application 3',

            applicationName: 'application name here',
            applicationAddress: 'application address here',
            applicationWebsite: 'application website here',

            favourite1: "granola",
            favourite2: "grapes",
            favourite3: "guacamole",

            areLoginDetailsInvalid: false,

            //result: "result will appear here",
            chosenAttribute: "chosen attribute will appear here",
            responseAvailable: false,

            mealToAdd: "banana",
            
            Items: [],            
            user: {
                mealsSelected: []
            },


        }
    },
    
    methods: {
        screen1() {
            this.screen = 1
        },
        screen2() {
            //this.getNewMeal();
            this.screen = 2
        },
        screen3() {
            this.screen = 3
        },
        screen4() {
            this.screen = 4
        },
        screen5() {
            this.screen = 5
        },
        screen6() {
            this.screen = 6
        },
        screen7() {
            this.screen = 7
        },
        screen8() {
            this.screen = 8
        },
        screen9() {
            this.screen = 9
        },
        screen10() {
            this.screen = 10
        },
        screen11() {
            this.screen = 11
        },
        screen12() {
            this.screen = 12
        },
        screen13() {
            this.screen = 13
        },
        screen14() {
            this.screen = 14
        },
        screen15() {
            this.screen = 15
        },
        screen16() {
            this.screen = 16
        },
        screen17() {
            this.screen = 17
        },
        screen18() {
            this.screen = 18
        },
        screen19() {
            this.screen = 19
        },
        screen20() {
            this.screen = 20
        },
        confirmAlert() {
            alert("Chosen meals registered, returning to home screen");
        },
        //make API GET request for new meal. Return meal name, meal image.
        async getNewMeal() {
            console.log(this.count);
            if (this.result == "" || this.count == 4 || this.currentMeal == "") {
                this.loadMoreMeals();
                this.count = 0;
            } else {
                this.count ++;
            }
            try {
            return Promise.resolve( this.currentMeal = this.result[this.count],
            this.currentMealRecipes = this.currentMeal.recipes,
            this.currentMealImage = "http://localhost:8080/" + this.currentMeal.imagePath)
            }
            catch(err) {
                alert("API RESPONSE TOO SLOW - RETURNING HOME"),
                this.screen1();
            }
        },
        //make API POST request to get names of recipe options for current meal.
        getCookOptions() {
            console.log("get cook options being called");
            //put recipes into Recipes[]
            console.log("TCR " + this.currentMeal.recipes);
            this.currentRecipes = this.currentMeal.recipes;
            console.log("CR " + this.currentRecipes);
            //then in the html we will do the loop through list ing thing to show the recipe name, then the recipe description
            //console.log(this.currentRecipes[0].name);
            //console.log(this.currentRecipes[1].name);

            for (let i =0; i<this.currentRecipes.length; i++) {
                this.recipeText = this.recipeText + this.currentRecipes[i].name + "<br/>" + this.currentRecipes[i].description + "<br/>";
            }

            console.log(this.recipeText);
            
            let element = document.getElementById("myDiv");
            element.innerHTML = this.recipeText;
        },
        //make API POST request to get names of restaurant options for current meal.
        getOrderOptions() {
            console.log("get order options being called");
            fetch("http://localhost:8080/hunter/getRestaurantsForMeal/${this.currentMeal.id}", method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    //'Content-Type' : 'application/json'
                },
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //make the given recipe title, the chosen recipe title
        makeChosenRecipe(title) {
            this.chosenRecipeTitle = title;
        },
        //make API POST request to get recipe content of the chosenRecipeTitle
        getChosenRecipeContent(title) {
            this.makeChosenRecipe(title);
        },
        //make the given restaurant name, the chosen restaurant name
        makeChosenRestaurant(name) {
            this.chosenRestaurantName = name;
        },
        //make API POST request to get restaurant address and restaurant website of chosenRestaurantName
        getChosenRestaurantData() {

        },
        
        //make API POST request to check if hunter account exists
        async loginHunterAPI() {
            console.log("here");
            await fetch("http://localhost:8080/hunter/login", method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    'Content-Type' : 'application/json'
                },
                body: JSON.stringify(
                    {
                        'username': this.hunterUsername,
                        'password': this.hunterPassword
                    }
                )
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                this.chosenAttribute = data.accessKey;
                this.accessKey = data.accessKey;
                //this.loginHunter();
                if (this.accessKey != '' && this.accessKey != undefined) {
                    console.log("AK " + this.accessKey),
                    console.log("AK GOOD")
                    //this.getNewMeal(),
                    this.screen2()
                } else (
                    console.log("AK BAD"),
                    this.accessKey = '',
                    alert("INCORRECT LOGIN DETAILS, RETURNING HOME"),
                    this.screen1()
                )
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
            console.log("login API finished working");
        },
        //make API GET request to get hunter profile detail
        getHunterProfile() {
            fetch("http://localhost:8080/hunter/profile", method = {
                method: "GET",
                headers: {
                    'accept': '*/*',
                    //'Content-Type' : 'application/json',
                    'Authorization': 'Bearer ' + this.accessKey
                }
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                this.result = data;
                this.chosenAttribute = data.name;
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //make API POST request to create new hunter account
        createNewHunterAccount() {
            fetch("http://localhost:8080/hunter/createNewHunterAccount/${this.hunterPasswordIn}/${this.hunterUsernameIn}'", method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    //'Content-Type' : 'application/json'
                },
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                if (data.status == 200) {
                    alert("ACCOUNT CREATED, PROCEED TO LOGIN"),
                    screen9();
                };
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        restaurantLogin() {
            //call the API to see if access key is given
            this.loginRestaurantAPI();
            //conduct if query based on whether access key given
            if (this.accessKey != '' && this.accessKey != undefined) {
                console.log("AK " + this.accessKey),
                console.log("AK GOOD"),
                this.getRestaurantMeals(),
                this.screen14()
            } else (
                console.log("AK BAD"),
                this.accessKey = '',
                alert("INCORRECT LOGIN DETAILS, RETURNING HOME"),
                this.screen1()
            )
        },
        //make API POST request to check if restaurant account exists
        loginRestaurantAPI() {
            fetch("http://localhost:8080/restaurant/login", method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    'Content-Type' : 'application/json'
                },
                body: JSON.stringify(
                    {
                        'username': this.restaurantUsername,
                        'password': this.restaurantPassword
                    }
                )
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                this.chosenAttribute = data.name;
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //make API POST request to create new restaurant account TEST
        createNewRestaurantAccount() {

        },
        //make API POST request to get restaurant's meal
        getRestaurantMeals() {

        },
        //make API POST request to add meals to restaurant
        addMealsToRestaurant() {
            this.screen1();
        },
        adminLogin() {
            //call the API to see if access key is given
            this.loginAdminAPI();
            //conduct if query based on whether access key given
            if (this.accessKey != '' && this.accessKey != undefined) {
                console.log("AK " + this.accessKey),
                console.log("AK GOOD"),
                //this.getRestaurantMeals(), method to get all meals
                this.screen20()
            } else (
                console.log("AK BAD"),
                this.accessKey = '',
                alert("INCORRECT LOGIN DETAILS, RETURNING HOME"),
                this.screen1()
            )
        },
        //make API POST request to check if admin exists
        loginAdminAPI() {
            fetch("http://localhost:8080/admin/login", method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    'Content-Type' : 'application/json'
                },
                body: JSON.stringify(
                    {
                        'username': this.restaurantUsername,
                        'password': this.restaurantPassword
                    }
                )
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                this.chosenAttribute = data.name;
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //API GET to get admin profile
        getAdminProfile() {
            fetch("http://localhost:8080/admin/profile", method = {
                method: "GET",
                headers: {
                    'accept': '*/*',
                    //'Content-Type' : 'application/json',
                    'Authorization': 'Bearer ' + this.accessKey
                }
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                this.result = data;
                this.chosenAttribute = data.name;
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //make API POST request to get names of restaurant applications
        getRestaurantApplicationNames() {

        },
        //set chosen application
        makeChosenApplication(applicationNameIn) {
            this.applicationName = applicationNameIn;
        },
        //make API POST request to get restaurant application data (address and website)
        getRestaurantApplicationData() {

        },
        //make API POST request to approve restaurant application
        approveChosenApplication() {

        },
        //make API POST request to deny restaurant application
        denyChosenApplication() {

        },
        //API POST for admin to add meals
        adminMealAdd() {
            fetch("http://localhost:8080/admin/meal/add", method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    'Content-Type' : 'multipart/form-data'
                },
                body: JSON.stringify(
                    {
                        mealToAdd,
                    }
                )
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                this.chosenAttribute = data.name;
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //API call for admin to get all users
        getAllUsers() {
            fetch("http://localhost:8080/admin/allUsers", method = {
                method: "GET",
                headers: {
                    'accept': '*/*',
                    'Authorization': 'Bearer ' + this.accessKey
                },
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                this.chosenAttribute = data.name;
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //API to load more meals
        loadMoreMeals() {
            console.log("loading more meals");
            return fetch("http://localhost:8080/hunter/meals", method = {
                method: "GET",
                headers: {
                    'accept': '*/*',
                    //'Authorization': 'Bearer ' + this.accessKey
                },
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                //this.chosenAttribute = data.name;
                //this.firstMeal = this.result[0];
                //this.firstMealImage = this.firstMealImage+this.firstMeal.imagePath;
                //console.log(this.firstMealImage);
                console.log("finished loading more meals");
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //testing API calls
        testPost() {
            fetch("https://petstore.swagger.io/v2/pet/193844", method = {
                method: "GET",
                headers: {
                    'Content-Type' : 'application/json'
                },
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                this.chosenAttribute = data.name;
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //Get recipes from meal
        getRecipes() {
            allRecipes: this.ch
        },
        ///////////////NEW METHODS STARTING HERE/////////////////////////////
        getNextMeal() {
            console.log("loading more meals");
            return fetch("http://localhost:8080/hunter/meals", method = {
                method: "GET",
                headers: {
                    'accept': '*/*',
                    //'Authorization': 'Bearer ' + this.accessKey
                },
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                console.log(this.result);
                //this.chosenAttribute = data.name;
                //this.firstMeal = this.result[0];
                //this.firstMealImage = this.firstMealImage+this.firstMeal.imagePath;
                //console.log(this.firstMealImage);
                console.log("finished loading more meals");
            })
            .then(setMeal => {
                if (this.counting == 4) {
                    this.counting = 0;
                }
                this.currentMeal = this.result[this.counting],
                this.currentMealRecipes = this.currentMeal.recipes,
                console.log(this.currentMealRecipes);
                this.currentMealImage = "http://localhost:8080/" + this.currentMeal.imagePath,
                this.counting = this.counting + 1
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        getRestaurantsForMeal() {
            fetch("http://localhost:8080/hunter/getRestaurantsForMeal/"+this.currentMeal.id, method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    'Authorization': 'Bearer '+ this.accessKey
                },
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
            })
            .then(setRestaurants => {
                this.currentMealRestaurants = this.result;
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        viewFavourites() {
            fetch("http://localhost:8080/hunter/viewFavourites", method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    'Authorization': 'Bearer '+ this.accessKey
                    //'Authorization' : 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW1vbiIsInJvbGVzIjpbIkhVTlRFUiJdLCJleHAiOjE2NDkzNTI5NTJ9.QC52qu3TJztjHdOs5prIyM_uAV0xPL8-8zWQuE6sMUY'
                },
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                this.Items = data;
                console.log("USER FAVS " + this.Items);
                
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        addToFavourites() {
            fetch("http://localhost:8080/hunter/addToFavourites/"+this.currentMeal.id, method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    //'Authorization': 'Bearer '+ this.accessKey,
                    'Authorization' : 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW1vbiIsInJvbGVzIjpbIkhVTlRFUiJdLCJleHAiOjE2NDkzNTI5NTJ9.QC52qu3TJztjHdOs5prIyM_uAV0xPL8-8zWQuE6sMUY'
                },
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
            })
            .then(checkStatus => {
                if (this.result == true) {
                    alert("SUCCESSFULLY ADDED TO FAVOURITES");
                } else {
                    alert("OOPS ERROR ADDING TO FAVOURITES");
                }
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        registerHunter() {
            fetch("http://localhost:8080/hunter/register", method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    'Content-Type': 'application/JSON'
                },
                body: JSON.stringify(
                    {
                        "username": this.hunterUsernameIn,
                        "password": this.hunterPasswordIn,
                        "address": this.hunterAddressIn,
                        "postcode": this.hunterPostcodeIn
                      }
                )
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                console.log("RESULT " + this.result);
            })
            .then(checkStatus => {
                if (this.result == true) {
                    alert("USER REGISTRATION SUCCESSFUL");
                    this.screen9();
                } else {
                    alert("USERNAME TAKEN - TRY AGAIN");
                }
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        registerRestaurant() {
            fetch("http://localhost:8080/restaurant/register", method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    'Content-Type': 'application/JSON'
                },
                body: JSON.stringify(
                    {
                        "username": this.restaurantUsernameIn,
                        "password": this.restaurantPasswordIn,
                        "description": this.restaurantDescriptionIn,
                        "address": this.restaurantAddressIn,
                        "postcode": this.restaurantPostcodeIn
                      }
                )
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                console.log("RESULT " + this.result);
            })
            .then(checkStatus => {
                if (this.result == true) {
                    alert("RESTAURANT REGISTRATION SUCCESSFUL");
                    this.screen12();
                } else {
                    alert("USERNAME TAKEN - TRY AGAIN");
                }
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //ADMIN LOGIN
        loginAdmin() {
            console.log("logging in admin");
            return fetch("http://localhost:8080/admin/login", method = {
                method: "POST",
                headers: {
                    'Content-Type' : 'application/json'
                },
                body: JSON.stringify({

                    username : this.adminUsername,
                    password : this.adminPassword
                })
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                console.log(this.result);
                console.log("finished logging in admin");
                console.log("username: " + this.adminUsername);
                console.log("password: " + this.adminPassword);
            })
            .then(nullAK => {
                this.accessKey = null;
                console.log("AK " + this.accessKey);
            })
            .then(setAK => {
                this.accessKey = null;
                this.accessKey = this.result.accessKey;
            })
            .then(checkAK => {
                if (this.accessKey != null) {
                    alert("LOGIN SUCCESSFUL"),
                    console.log(this.accessKey);
                    this.screen20();
                } else {
                    alert("LOGIN UNSUCCESSFUL")
                }
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //HUNTER LOGIN
        loginHunter() {
            console.log("logging in hunter");
            return fetch("http://localhost:8080/hunter/login", method = {
                method: "POST",
                headers: {
                    'Content-Type' : 'application/json'
                },
                body: JSON.stringify({

                    username : this.hunterUsername,
                    password : this.hunterPassword
                })
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                console.log(this.result);
                console.log("finished logging in hunter");
                console.log("username: " + this.hunterUsername);
                console.log("password: " + this.hunterPassword);
            })
            .then(nullAK => {
                this.accessKey = null;
                console.log("AK " + this.accessKey);
            })
            .then(setAK => {
                this.accessKey = null;
                this.accessKey = this.result.accessKey;
            })
            .then(checkAK => {
                if (this.accessKey != null) {
                    alert("LOGIN SUCCESSFUL"),
                    console.log(this.accessKey);
                    this.screen2();
                } else {
                    alert("LOGIN UNSUCCESSFUL")
                }
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //RESTAURANT LOGIN
        loginRestaurant() {
            console.log("logging in restaurant");
            return fetch("http://localhost:8080/restaurant/login", method = {
                method: "POST",
                headers: {
                    'Content-Type' : 'application/json'
                },
                body: JSON.stringify({

                    username : this.restaurantUsername,
                    password : this.restaurantPassword
                })
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                console.log(this.result);
                console.log("finished logging in restaurant");
                console.log("username: " + this.restaurantUsername);
                console.log("password: " + this.restaurantPassword);
            })
            .then(nullAK => {
                this.accessKey = null;
                console.log("AK " + this.accessKey);
            })
            .then(setAK => {
                this.accessKey = null;
                this.accessKey = this.result.accessKey;
            })
            .then(checkAK => {
                if (this.accessKey != null) {
                    alert("LOGIN SUCCESSFUL"),
                    console.log(this.accessKey);
                    this.screen14();
                } else {
                    alert("LOGIN UNSUCCESSFUL")
                }
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        adminViewAllMeals() {
            fetch("http://localhost:8080/admin/allMeals", method = {
                method: "GET",
                headers: {
                    'accept': '*/*',
                    'Authorization': 'Bearer '+ this.accessKey
                },
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                console.log("RESULT " + this.result);
            })
            .then(setItems => {
                this.Items = this.result;
                console.log("ITEMS " + this.Items);
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        adminAddMeal() {
            fetch("http://localhost:8080/admin/addMeal/" +this.newMealImgPath+"/"+this.newMealName, method = {
                method: "POST",
                headers: {
                    'accept': '*/*',
                    'Authorization': 'Bearer '+ this.accessKey
                },
            })
            .then(response => response.json())
            .then(data => {
                this.result = data;
                console.log("RESULT " + this.result);
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        logout() {
            this.accessKey = null;
            if (this.accessKey == null) {
                alert("LOGOUT SUCCESSFUL - RETURNING TO HOME PAGE"),
                this.screen1();
            } else {
                alert("LOGOUT UNSUCCESSFUL")
            }
        }
    }
    
    
});

app.mount('#app');