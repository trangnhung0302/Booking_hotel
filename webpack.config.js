const path = require('path');

module.exports = {
  entry: './src/main/resources/static/js/index.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'src/main/resources/static/js')
  }
};