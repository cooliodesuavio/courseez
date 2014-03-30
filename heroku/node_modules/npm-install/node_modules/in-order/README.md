# in-order

Asynchronously operate over an array in order

## Example

```js
var serial = require("in-order")

serial([1, 2, 3], function (item, callback) {
    console.log(item)
    setTimeout(callback, 1000)
}, function (err) {
    console.log("all done")
})
```

## Installation

`npm install in-order`

## Contributors

 - Raynos

## MIT Licenced
