define(function (require) {

    let $ = require("jquery");

    let Root = function () {
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

        $("#editContact").on("click", function (event) {
            let id = $(".selected").attr("uuid");
            self.editContact(id);
        });

        $("#deleteContact").on("click", function (event) {
            let id = $(".selected").attr("uuid");
            self.deleteContact(id);
        });

        $("#clearInputFields").on("click", function (event) {
            self.clearInputFields();
        });

        $("#searchContact").on("click", function (event) {
            self.searchContacts();
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
                self.clearInputFields();
            },
            error: function (error) {
                console.log("No Contacts");
            }
        });
    }

    Root.prototype.searchContacts = function () {
        let self = this;
        let text = $("#search").val();

        if (text) {
            $.ajax({
                type: "POST",
                contentType: 'text/plain',
                data: text,
                url: "api/v1/contact/search",
                cache: false,
                success: function (data) {
                    console.log("Founded results!");
                    self.listContacts(data);
                },
                error: function (error) {
                    console.log("Error!");
                }
            });
        }
    }

    Root.prototype.listContacts = function (data) {
        $("#contactTable>tbody").empty();

        for (let i = 0; i < data.length; i++) {
            let contact = data[i];
            let row = "<tr uuid='" + contact.id + "'>" +
                "<td class='rowName'>" + contact.name + "</td>" +
                "<td class='rowNumber'>" + contact.number + "</td>" +
                "</tr>";
            $("#contactTable>tbody").append(row);
        }

        $("tr").on("click", function (event){
            $("tr").removeClass("selected");
            let targetRow = $(event.target).parents("tr");
            targetRow.addClass("selected");
            $("#name").val(targetRow.find(".rowName").text());
            $("#number").val(targetRow.find(".rowNumber").text());
        });
    }

    Root.prototype.addContact = function () {
        let self = this;
        let name = $("#name").val();
        let number = $("#number").val();

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

    Root.prototype.editContact = function (id) {
        let self = this;
        let name = $("#name").val();
        let number = $("#number").val();

        if (name && number) {
            let requestData = JSON.stringify({
                "name": name,
                "number": number
            })
            $.ajax({
                type: "PUT",
                contentType: 'application/json',
                data: requestData,
                url: "api/v1/contact/" + id,
                cache: false,
                success: function (data) {
                    console.log("Contact updated!");
                    self.getContacts();
                },
                error: function (error) {
                    console.log("Error!");
                }
            });
        }
    }

    Root.prototype.deleteContact = function (id) {
        if ($(".selected").length) {
            let self = this;
            $.ajax({
                type: "DELETE",
                url: "api/v1/contact/" + id,
                cache: false,
                success: function (data) {
                    console.log("Contact deleted!");
                    self.getContacts();
                    self.clearInputFields();
                },
                error: function (error) {
                    console.log("Error!");
                }
            });
        }
    }

    Root.prototype.clearInputFields = function () {
        $("#search").val("");
        $("#name").val("");
        $("#number").val("");
        $("tr").removeClass("selected");
    }

    return Root;

});
