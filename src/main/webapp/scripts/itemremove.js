/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const List = document.querySelector(".item_list");
const amount = document.querySelector(".price");

List.addEventListener('click',(event)=>{

            if(event.target.classList.contains('remove')){
                let productId = event.target.dataset.id;
                let price = event.target.dataset.price;
                const sentdata = { product: productId ,price: price};  
                fetch('MinusServlet', {
                    method: 'POST',
                    headers: {
                                'Content-Type': 'application/json'
                             },
                                body: JSON.stringify(sentdata)
                             })
                             .then(response => {
                             if (!response.ok) {
                                throw new Error('Network response was not ok');
                                }
                                return response.json(); 
                                })
                                .then(data => {
                                console.log(data);
                                    amount.innerHTML = "R"+data.price+".00";
                                    event.target.closest(".item").remove();
                                })
                                .catch(error => {
                                            console.error('Error:', error);
                                });                
            }
             
});