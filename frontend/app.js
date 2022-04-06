const app = Vue.createApp({

    data() {
        return {
            screen: 44,

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
            hunterPassword: '12345678',
            accessKey: '',

            hutnerUsernameIn: '',
            hunterPasswordIn: '',
            hunterPasswordReIn: '',

            restaurantUsername: 'damon',
            restaurantPassword: '12345678',

            restaurantUsernameIn: '',
            restaurantPasswordIn: '',
            restaurantPasswordReIn: '',
            restaurantAddressIn: '',
            restaurantWebsiteIn: '',

            mealOptions: [{meal: 'granola'}],
            mealsChosen: [],

            adminUsername: '',
            adminPassword: '',

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

            result: "result will appear here",
            chosenAttribute: "chosen attribute will appear here",
            responseAvailable: false,

            mealToAdd: "banana",

        }
    },
    
    methods: {
        screen1() {
            this.screen = 1
        },
        screen2() {
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
        //make API GET request for new meal. Return meal name, meal image.
        getNewMeal() {

        },
        //make API GET request for specific meal. Return meal name, meal image.
        getSpecificMeal(meal) {

        },
        //log the user out. Reset the usernames and passwords, return to home screen.
        logout() {
            this.screen1();
        },
        //make API POST request to add current meal to favourites.
        addToFavourites() {

        },
        //make API POST request to get names of recipe options for current meal.
        getCookOptions() {

        },
        //make API POST request to get names of restaurant options for current meal.
        getOrderOptions() {

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
        checkIfHunterExists() {
            fetch("http://localhost:8080/hunter/login", method = {
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
            })
            .catch(err => {
                console.error("Error! " + err);
                alert("Could not connect to the server!");
            });
        },
        //make API GET request to get hunter profile detail
        getHunterProfile() {
            fetch("http://localhost:8080/hunter/0/profile", method = {
                method: "GET",
                headers: {
                    'accept': '*/*',
                    //'Content-Type' : 'application/json',
                    'Authorization': 'Bearer ' + this.accessKey
                }
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
        //make API POST request to create new hunter account
        createNewHunterAccount() {

        },
        //make API POST request to check if restaurant account exists
        checkIfRestaurantExists() {
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
        //make API POST request to check if admin exists
        checkIfAdminExists() {

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
    }
    
    
});

app.mount('#app');