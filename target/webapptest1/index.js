var app = new Vue({
    el: '#app',
    data: {
       titles: null,
       newTitle: "asd"
    },
    methods: {
        loadList: function () {
            axios
              .get('/rest/scanner/list')
              .then(response => (this.titles = response.data))
        },
        addItem: function () {
            console.log("new Title: " + this.newTitle)
            axios
                .post('/rest/scanner/title', this.newTitle)
                .then(function (response) {
                    console.log("response: " + response);
                    axios
                        .get('/rest/scanner/list')
                        .then(response => (this.titles = response.data))
                })
                .catch(function (error) {
                    console.log("Error: " + error);
                });

        },
        deleteItem: function (title) {
            console.log("deleting title: " + title);
            axios
                .delete('/rest/scanner/title/'+title)
                .then(function (response) {
                })
                .catch(function (error) {
                    console.log("Error: " + error);
                });
        }
    },
    mounted () {
         axios
            .get('/rest/scanner/list')
            .then(response => (this.titles = response.data))
    }
});
