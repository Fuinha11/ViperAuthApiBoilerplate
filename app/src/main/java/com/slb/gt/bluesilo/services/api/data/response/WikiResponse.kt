package com.slb.gt.bluesilo.services.api.data.response

class WikiResponse {
    class Result(val query: Query) : BaseResponse()
    class Query(val searchinfo: SearchInfo)
    class SearchInfo(val totalhits : Int)
}