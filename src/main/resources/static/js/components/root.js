define(function (require){

    let $ = require("jquery");

    let Root = function (){
        this.init();
    };

    Root.prototype.init = function () {
        this.getContacts();
        this.installEventHandlers();
    }

    Root.prototype.installEventHandlers = function () {
        let self = this;
        $("#refresh").on("click", function (event) {
            self.getContacts();
        });

        $("#addContact").on("click", function (event) {
            self.addContact();
        });
    }

    Root.prototype.getContacts = function () {
        let self = this;
        $.ajax({
            type: "GET",
            url: "api/v1/contact",
            cache: false,
            success: function (data) {
                self.listContacts(data);
            },
            error: function (error) {
                console.log("No Contacts");
            }
        });
    }

    Root.prototype.listContacts = function (data) {
        $("#contactList").empty();

        for (let i = 0; i < data.length; i++) {
            let contact = data[i];
            let li = "<li><div>" +
                "<span>" + contact.name + "</span>" +
                "<span>&#9;-&#9;</span>" +
                "<span>" + contact.number + "</span>" +
                "<button contact-uuid='" + contact.id + "' class='edit'>Edit</button>" +
                "<button contact-uuid='" + contact.id + "' class='delete'>Delete</button>" +
                "</div></li>";

            $("#contactList").append(li);
        }
    }

    Root.prototype.addContact = function () {
        let self = this;
        let name = $("#name").val();
        let number = $("#number").val();

        console.log(name, number);
        if (name && number) {
            let requestData = JSON.stringify({
                "name": name,
                "number": number
            })
            $.ajax({
                type: "POST",
                contentType: 'application/json',
                data: requestData,
                url: "api/v1/contact",
                cache: false,
                success: function (data) {
                    console.log("Contact added!");
                    self.getContacts();
                },
                error: function (error) {
                    console.log("Error!");
                }
            });
        }
    }

    return Root;

});
