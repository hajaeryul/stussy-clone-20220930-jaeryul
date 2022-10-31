class CollectionsApi {
    static #instatnce = null;

    static getInstance() {
        if(this.#instatnce == null) {
            this.#instatnce = new CollectionsApi();
        }
        return this.#instatnce;
    }

    getCollections(page) {
        let responseData = null;

        const url = location.href;
        const category = url.substring(url.lastIndexOf("/") + 1);

        $.ajax({
            async: false,
            type: "get",
            url: "/api/collections/" + category,
            data: {
                "page": page
            },
            dataType: "json",
            success: (response) => {
                responseData = response.data;
            },
            error: (error) => {
                console.log(error);
            }
        });

        return responseData;
    }

}

class PageScroll {

    static #instatnce = null;

    static getInstance() {
        if(this.#instatnce == null) {
            this.#instatnce = new PageScroll();
        }
        return this.#instatnce;
    }

    addScrollPagingEvent() {
        const html = document.querySelector("html");
        const body = document.querySelector("body");
        
        window.onscroll = () => {
            // console.log("문서 전체 높이: " + body.offsetHeight);
            // console.log("눈에 보이는 영역 높이: " + html.clientHeight);
            // console.log("스크롤의 상단 위치: " + html.scrollTop);
            let scrollStatus = body.offsetHeight - html.clientHeight - html.scrollTop;
            console.log("현재 스크롤 상태: " + scrollStatus);
            if(scrollStatus > -50 && scrollStatus < 50) {
                const nowPage = CollectionsService.getInstance().collectionsEntity.page;
                CollectionsService.getInstance().collectionsEntity.page = Number(nowPage) + 1;
                CollectionsService.getInstance().loadCollections();
            }
        }
    }
}

class CollectionsService {
    static #instatnce = null;

    static getInstance() {
        if(this.#instatnce == null) {
            this.#instatnce = new CollectionsService();
        }
        return this.#instatnce;
    }

    pdtIdList = null;

    collectionsEntity = {
        page: 1,
        totalCount: 0,
        maxPage: 0
    }

    constructor() {
        this.pdtIdList = new Array();
    }


    loadCollections() {
        if(this.collectionsEntity.page == 1 || this.collectionsEntity.page < Number(this.collectionsEntity.maxPage) + 1) {
            const responseData = CollectionsApi.getInstance().getCollections(this.collectionsEntity.page);
            if(responseData.length > 0 ){
                this.collectionsEntity.totalCount = responseData[0].productTotalCount;
                this.collectionsEntity.maxPage = responseData[0].productTotalCount % 16 == 0 
                                                ? responseData[0].productTotalCount / 16
                                                : Math.floor(responseData[0].productTotalCount / 16) + 1;
                this.getCollections(responseData);
            }else {
                alert("해당 카테고리에 등록된 상품 정보가 없습니다.");
                location.href = "/collections/all";
            }
        }
        
        
    }

    getCollections(responseData) {
        const collectionProducts = document.querySelector(".collection-products");
        
        responseData.forEach(product => {
            this.pdtIdList.push(product.productId);
            collectionProducts.innerHTML += `
            <li class="collection-product">
                <div class="product-img">
                    <img src="/static/upload/product/${product.mainImg}">
                </div>
                <div class="product-name">
                    ${product.productName}
                </div>
                <div class="product-price">
                    ${product.productPrice}원
                </div>
            </li>
            `;
        });
        this.addProductListEvent();
    }

    addProductListEvent() {
        const collectionProducts = document.querySelectorAll(".collection-product");

        collectionProducts.forEach((product, index) => {
            product.onclick = () => {
                location.href = "/product/" + this.pdtIdList[index];
            }
        })
    }
}

window.onload = () => {
    CollectionsService.getInstance().loadCollections();
    PageScroll.getInstance().addScrollPagingEvent();
}