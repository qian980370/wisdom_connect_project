
export const CHANGE_LOGIN = (state, user)=>{
    state.Authorization = user.Authorization;
    localStorage.setItem('Authorization', user.Authorization);
};