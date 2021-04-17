# Product API Demo

Added APIs as mention below. Added API Test for the same.   

### Sample product data

```
{"product_id":"<fill>","sellerId":"<fill>","title":"<fill>","manufacturer":"<fill>","price":{"range":"<fill>","min":<fill>,"max":<fill>},"isLowQuantity":false,"isSoldOut":false,"isBackorder":false,"metafields":[{"key":"Capacity","value":"<fill>"},{"key":"<fill>","value":"<fill>"}],"requiresShipping":true,"isVisible":true,"publishedAt":{"$date":"2020-02-12T08:05:39.743Z"},"createdAt":{"$date":"2010-08-23T05:53:16.134Z"},"updatedAt":{"$date":"2019-08-23T05:53:16.134Z"},"workflow":{"status":"new"}}
```
```
{"product_id":"121234353567786","sellerId":"87866","title":"Voltas 1.5 Ton Adjustable Inverter 5 Star Copper (2019 Range) 185V ADS (R32) Split AC (White)","pageTitle":"Voltas 1.5 Ton Adjustable Inverter.","description":"Stay at ease even when the mercury hits the roof by opting for this Voltas Inverter Adjustable Split AC. It has a capacity of 1.5 tons, hence it can cover a large area, while the 5-star energy rating helps you to reduce electricity costs significantly. It ensures stabilizer free operation so that it stays protected against voltage fluctuations. The 100 percent copper make ensures optimum performance as well as energy efficiency. (Star rating of model is as per 2018/2019 BEE rating, manufacturing can be of 2019 or before)","manufacturer":"Voltas","price":{"range":"24999","min":24999,"max":24999},"isLowQuantity":false,"isSoldOut":false,"isBackorder":false,"metafields":[{"key":"Capacity","value":"1.5 ton"},{"key":"Rating","value":"Inverter 5 Star"}],"requiresShipping":true,"isVisible":true,"publishedAt":{"$date":"2020-02-12T08:05:39.743Z"},"createdAt":{"$date":"2010-08-23T05:53:16.134Z"},"updatedAt":{"$date":"2019-08-23T05:53:16.134Z"},"workflow":{"status":"new"}}
```

# REST APIs 

Create REST API which has 

* POST endpoint to create products 
`POST /product/`
```
{
    "product_id":"1",
    "sellerId":"naman.mehta@gmail.com",
    "title":"Book 1",
    "manufacturer":"Personal",
    "metaFields":
    [
        {
        "key": "1",
        "value": "1"
        },
        {
        "key": "2",
        "value": "2"
        }
    ],
    "requiresShipping": true,
    "isVisible": true,
    "workFlow": {
        "status": "new"
    }
}
```

* GET endpoint to fetch product details
`GET /product/prodcutID`

* PUT end point to update product details
`PUT /product/prodcutID`
```
{
    "sellerId": "naman.mehta@gmail.com",
    "title": "Book 1",
    "manufacturer": "Personal",
    "price": {
        "min": 100,
        "max": 100,
        "range": 100
    },
    "metaFields": [
        {
            "key": "1",
            "value": "2"
        },
        {
            "key": "2",
            "value": "3"
        }
    ],
    "requiresShipping": true,
    "publishedAt": {
        "$date": "2021-04-16T11:18:59.737Z"
    },
    "createdAt": {
        "$date": "2021-04-16T11:18:59.737Z"
    },
    "updatedAt": null,
    "workFlow": {
        "status": "new"
    },
    "visible": true,
    "lowQuantity": false,
    "soldOut": false,
    "backorder": false
}
```

* DELETE end point to delete product details
`DELETE /product/prodcutID`

All above APIs should do basic validation and return appropriate responses code with the responses

Create another REST API which has

* POST endpoint to create products price (one product at a time)
`POST /product/price`
```
{
	"product_id":"1",
	"price":{
		"range":"4.00-5.00",
		"min":4.0,
		"max":5.0
	}
}
```
* GET endpoint to fetch product price (for a product)
`GET /product/price/productId`

### Sample data

* Product data
{"product_id":"<fill>","sellerId":"<fill>","title":"<fill>","manufacturer":"<fill>","isLowQuantity":false,"isSoldOut":false,"isBackorder":false,"metafields":[{"key":"Capacity","value":"<fill>"},{"key":"<fill>","value":"<fill>"}],"requiresShipping":true,"isVisible":true,"publishedAt":{"$date":"2020-02-12T08:05:39.743Z"},"createdAt":{"$date":"2010-08-23T05:53:16.134Z"},"updatedAt":{"$date":"2019-08-23T05:53:16.134Z"},"workflow":{"status":"new"}}

* Price data
{"product_id":"1212323","price":{"range":"4.00-5.00","min":4.50,"max":5.00}}