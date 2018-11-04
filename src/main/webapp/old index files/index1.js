var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
});
var app4 = new Vue({
  el: '#app-4',
  data: {
    titles: [
      { text: 'Learn JavaScript' },
      { text: 'Learn Vue' },
      { text: 'Build something awesome' }
    ]
  }
});
var app5 = new Vue({
  el: '#app-5',
  data: {
    message: 'Hello Vue.js11111!'
  },
  methods: {
    reverseMessage: function () {
      this.message = this.message.split('').reverse().join('')
    }
  }
});

var app7 = new Vue({
    el: '#app-7',
    data: {
       titles: null
    },
     mounted () {
        axios
          .get('/rest/scanner/list')
          .then(response => (this.titles = response))
      }
});
