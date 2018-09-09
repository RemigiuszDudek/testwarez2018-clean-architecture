import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'get'
        urlPath '/content'
    }
    response {
        status 200
        body([
                [productId: 'id_1', quantity: 1],
                [productId: 'id_2', quantity: 2],
        ])
    }
}