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

class PageNumber {
    #page = 0;
    #maxPageNumber = 0;
    #pageNumberList = null;

    constructor(page, totalCount) {
        this.#page = page;
        this.#totalCount = totalCount;
        this.#maxPageNumber = totalCount % 16 == 0 ? Math.floor(totalCount / 16) : Math.floor(totalCount / 16) + 1;
        this.#pageNumberList =  document.querySelector(".page-number-list");
        this.#pageNumberList.innerHTML = ``;
        this.loadPageNumbers();
    }

    loadPageNumbers() {
        this.createPreButton();
        this.createNumberButtons();
        this.createNextButton();
    }

    createPreButton() {
        if(this.#page != 1) {
            this.#pageNumberList.innerHTML += `
            <a href=""><li>&#60;</li></a>
            `;

        }
    }
    
    createNumberButtons() {
        const startIndex = this.#page % 5 == 0 ? this.#page - 4 : this.#page - (this.#page % 5) + 1;
        const endIndex = startIndex + 4 <= this.#maxPageNumber ? startIndex + 4 : this.#maxPageNumber;
        
        for(let i = startIndex; i <= endIndex; i++) {
            this.#pageNumberList.innerHTML += `
            <a href="javascript:void(0)"><li>${i}</li></a>
            `;
        }
    }

    createNextButton() {
        if(this.#page != this.#maxPageNumber) {
            this.#pageNumberList.innerHTML += `
            <a href=""><li>&#62;</li></a>
            `;
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

    collectionsEntity = {
        page: 1,
        totalCount: 0
    }

    loadCollections(page) {
        const responseData = CollectionsApi.getInstance().getCollections(this.collectionsEntity.page);
        this.collectionsEntity.totalCount = responseData[0].productTotalCount;

        new PageNumber(this.collectionsEntity.page, this.collectionsEntity.totalCount);
    }
}

window.onload = () => {
    CollectionsService.getInstance().loadCollections();
}