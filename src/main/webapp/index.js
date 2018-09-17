var app = new Vue({
    el: '#app',
    data: {
       titles: null
    },
     mounted () {
        axios
          .get('/rest/scanner/list')
          .then(response => (this.titles = response.data))
      }
});
