import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'PUT'
        urlPath '/products'
        headers {
            header(contentType(), applicationJsonUtf8())
        }
        body([
                [productId: 'id_1', quantity: 1],
                [productId: 'id_2', quantity: 2],
        ])
    }
    response {
        status 200
        body([
                [productId: 'id_1', status: 'SUCCESS', message: ''],
                [productId: 'id_2', status: 'SUCCESS', message: '']
        ])
    }
}