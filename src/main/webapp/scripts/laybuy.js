/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const laybuy = document.querySelector(".laybuy");
const overlay = document.querySelector('.overlay');
const container = document.querySelector('.form_container');
const search = document.querySelector('.search-txt');
const output = document.querySelector('.results');
const searchText = document.querySelector('.search_txt');
const out = document.querySelector('.outcomes');
const pay = document.querySelector(".payment");
const cash = document.querySelector('.cash_container');
const cardSearch = document.querySelector('.card_search_txt');
const cardout = document.querySelector('.card_outcomes');
const cardpay = document.querySelector(".card");
const card = document.querySelector('.card_container');


laybuy.addEventListener("click",()=>{
    search.value='';
    output.innerHTML = '';
    overlay.classList.toggle('non_visible');
    container.classList.toggle('non_visible');
    
});

pay.addEventListener("click",()=>{
    searchText.value='';
    out.innerHTML = '';
    overlay.classList.toggle('non_visible');
    cash.classList.toggle('non_visible');
    
});

cardpay.addEventListener("click",()=>{
    cardSearch.value='';
    cardout.innerHTML = '';
    overlay.classList.toggle('non_visible');
    card.classList.toggle('non_visible');
    
});

overlay.addEventListener('click',(e)=>{
    
    search.value = '';
    output.innerHTML = '';
        cash.classList.add('non_visible');
        container.classList.add('non_visible');
        card.classList.add('non_visible');
    overlay.classList.toggle('non_visible');
});