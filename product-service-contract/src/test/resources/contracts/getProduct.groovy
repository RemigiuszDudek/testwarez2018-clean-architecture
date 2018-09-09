package orderReceiver

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        urlPath '/product/123'
    }
    response {
        status 200
        headers {
            header(contentType(), applicationJsonUtf8())
        }
        body([
                productId: '123',
                name     : 'product-name',
                volume   : 2
        ])
    }
}