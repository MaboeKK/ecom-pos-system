/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const searchbtn = document.querySelector(".search-btn");
const searchtxt = document.querySelector(".search-txt");
const result = document.querySelector(".results");
const newuser = document.querySelector(".newuser");
const tabs = document.querySelector(".tabs");
const divs = document.querySelectorAll('.div');

const findBtn = document.querySelector(".search_btn");
const searchTxt = document.querySelector(".search_txt");
const outcomes = document.querySelector(".outcomes");
const neweruser = document.querySelector(".neweruser");
const Divs = document.querySelectorAll('.Divide');

const cardfindBtn = document.querySelector(".card_search_btn");
const cardsearchTxt = document.querySelector(".card_search_txt");
const cardoutcomes = document.querySelector(".card_outcomes");
const cardneweruser = document.querySelector(".card_neweruser");
const dividers = document.querySelectorAll('.Divider');

searchbtn.addEventListener('click',()=>{
    const customer = {customer: searchtxt.value};
            fetch('GetCustomerServlet', {
                method: 'POST',
                    headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(customer)
                        })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                                return response.json(); 
                            })
                        .then(data => {
                         
                            result.innerHTML = '';
                            console.log(data);
                            if(data.id!=0){
                                
                                result.innerHTML = `<div>
                                                    <div class="names">
                                                        <span class="name-span">${data.name}</span>
                                                        <span class="surname-span">${data.surname}</span>    
                                                    </div>
                                                    <div class="contacts">
                                                        <div class="email">${data.email}</div>
                                                        <div class="phone">${data.phone.toString().slice(0,3)+" "+data.phone.toString().slice(3,6)+" "+data.phone.toString().slice(6,9)}</div>    
                                                    </div>                     
                                                    <a class="laybuy-btn" href="CreateOrderServlet?customer=${data.id}">Laybuy</a>
                                                    </div>`;                                
                            }else{
                                result.innerHTML = `<div class="not_customer">${"Customer not registered"}`;
                            }   
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });     
});

findBtn.addEventListener('click',()=>{
    
    const customer = {customer: searchTxt.value};
            fetch('GetCustomerServlet', {
                method: 'POST',
                    headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(customer)
                        })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                                return response.json(); 
                            })
                        .then(data => {
                            console.log(data);
                            outcomes.innerHTML = '';
                            if(data.id!=0){
                                outcomes.innerHTML = `<div class="customer">
                                                    <div class="names">
                                                        <span class="name-span">${data.name}</span>
                                                        <span class="surname-span">${data.surname}</span>                                                    
                                                    </div>
                                                    <div class="contacts">
                                                        <div class="phone">${data.email}</div>
                                                        <div class="email">${data.phone.toString().slice(0,3)+" "+data.phone.toString().slice(3,6)+" "+data.phone.toString().slice(6,9)}</div>
                                                    </div>
                                                    <a class="laybuy-btn" href="CreateSaleServlet?customer=${data.id}&method=cash">Buy</a>
                                                    </div>`;                                
                            }else{
                                outcomes.innerHTML = `<div class="not_customer">${"Customer not registered"}</div>`;
                            }   
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });     
});

cardfindBtn.addEventListener('click',()=>{
    
    const customer = {customer: cardsearchTxt.value};
            fetch('GetCustomerServlet', {
                method: 'POST',
                    headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(customer)
                        })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                                return response.json(); 
                            })
                        .then(data => {
                            
                            cardoutcomes.innerHTML = '';
                            if(data.id!=0){
                                cardoutcomes.innerHTML = `<div>
                                                    <div class="names">
                                                        <span class="name-span">${data.name}</span>
                                                        <span class="surname-span">${data.surname}</span>                                                    
                                                    </div>
                                                    <div class="contacts">
                                                        <div class="phone">${data.email}</div>
                                                        <div class="email">${data.phone.toString().slice(0,3)+" "+data.phone.toString().slice(3,6)+" "+data.phone.toString().slice(6,9)}</div>
                                                    </div>
                                                    <a class="laybuy-btn" href="CreateSaleServlet?customer=${data.id}&method=card">Buy</a>
                                                    </div>`;                                
                            }else{
                                console.log("out");
                                cardoutcomes.innerHTML = `<div class="not_customer">${"Customer not registered"}</div>`;
                            }   
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });     
});

document.querySelector('.tabs').addEventListener('click', (e) => {
    let target = e.target;

    // Check if the clicked element is a tab
    if (target.classList.contains('tab')) {
        // Hide all divs
        divs.forEach((d) => {
            d.classList.add('non_visible');
        });
        result.innerHTML = '';
        searchtxt.textContent = '';
        // Show the div corresponding to the clicked tab
        const num = target.dataset.num;
        const activeDiv = document.querySelector(`.div_${num}`);
        if (activeDiv) {
            activeDiv.classList.remove('non_visible');
        }
    }
});

document.querySelector('.Tablets').addEventListener('click', (e) => {
    let targeted = e.target;

    // Check if the clicked element is a tab
    if (targeted.classList.contains('Tablet')) {
        // Hide all divs
        Divs.forEach((div) => {
            div.classList.add('non_visible');
        });
        outcomes.innerHTML = '';
        searchTxt.textContent = '';
        // Show the div corresponding to the clicked tab
        const number = targeted.dataset.num;
        const activeDIV = document.querySelector(`.Divide_${number}`);
        if (activeDIV) {
            activeDIV.classList.remove('non_visible');
        }
    }
});

document.querySelector('.Choices').addEventListener('click', (e) => {
    let targeter = e.target;

    // Check if the clicked element is a tab
    if (targeter.classList.contains('Choice')) {
        // Hide all divs
        dividers.forEach((div) => {
            div.classList.add('non_visible');
        });
        cardoutcomes.innerHTML = '';
        cardsearchTxt.textContent = '';
        // Show the div corresponding to the clicked tab
        const Number = targeter.dataset.num;
        const activedivider = document.querySelector(`.Divider_${Number}`);
        if (activedivider) {
            activedivider.classList.remove('non_visible');
        }
    }
});
