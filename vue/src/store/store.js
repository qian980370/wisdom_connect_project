
import * as mutations from './mutations';

const store = new Vuex.Store({
    state: {
        // 存储token
        Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : ''
    },
    mutations
});

export default store;
