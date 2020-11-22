There are various ways a supermarket can price their goods, which will affect the underlying data modeling. These scenarios will need to be handled:

1.)  Product X costs Y

2.)  X number of products for Y price

3.)  Y price per X unit of measure

4.)  Get free Z product(s) X with purchase of Y product(s) X

5.)  X% discount with purchase of Y product(s)

6.)  X% discount off additional product X with purchase of Y product(s) X

7.)  Get X products for the cost of Y products

8.)  X% off select products

9.)  X amount off select products

10.) X% off when spending Y or more

I will use a relational model as a starting point, since a document model doesn't really make sense here (there isn't some large chunk of data that is regularly accessed as a whole) and I'm not too familiar with others like hierarchical or network data models. 

My first thought is that 'raw' prices should be decoupled from the existing deals. Products can be modeled initially in a simple way with columns product_id, name, price, and unit_type. unit_type would refer to how the product quantity is measured, e.g. per unit, per X length (maybe for fabrics), or per pound, etc. Unit_type should partly address scenario 3. Deals will be a bit harder to model, as there are many different types of deals. Given the variety of deals, I don't think they can all be encapsulated in a single table. 

I was briefly thinking that maybe deals can just all be handled in the code, but that would not be good for maintainability, since it would be difficult to get a list of deals offered without traversing the code, as well as having no good way of looking up deals.

Continuing with a table for the deals, I think we would need a table for each deal type. First we'll need to categorize deal types. Deals themselves are always contingent upon some factor - whether it is buying a certain product or buying a certain amount of products, etc. There is no deal without a purchase. From this, we can generalize an initial Deals table with deal_id, name, percent_discount, quantity (required purchase quantity for the deal), and a foreign key of product_id. Scenario 4, 5, 8 will be handled by this initial table. With scenario 4, percent_discount would be 100%, and for scenario 8, quantity would be 1. 6 can be addressed with a slight modification - adding a column additional_product indicating the number of additional products the discount would apply to (rather than on the total number of that product), and where 0 could indicate that it would apply on the total number of that product. While at first 2 and 7 are seemingly incongruous with a percentage discount, it can be translated into such. The discount percentages can be calculated. For 2, the quantity would be X, and the percent_discount can be calculated as ((x * product.price) - Y)/(X * product.price). For 7, it can be calculated as ((X * product.price) - (Y * product.price))/(X * product.price). The 2 calculations can be multiplied by 100 for an actual percent. The benefit of this calculation is that it allows these deal types to be included in the same table, however the drawback is that it obscures the human readable details and is harder to understand in a sales context.

9 and 10 are scenarios that seem like they won't fall under this table, since the deals are based on flat price amounts, whether it is a pre-requisite spend amount or discount amount. We could add columns to the original deal table to include flat price requirement or flat price discount, and so a non-null value in flat_price (flat price requirement) or in flat_discount (flat price discount) would have a null in quantity or percent_discount respectively. The other option is to have them in different tables. Since there are only these 2 that I've brainstormed earlier, it doesn't make sense to me to have them in a different table, since getting all deals would require hitting multiple tables with joins which also increases that read time. With more flat priced based discounts, it may make more sense to have different tables, and it would also eliminate the transitive functional dependencies between flat_price and quantity or flat_discount and percent_discount. If we keep it in one table, it could potentially introduce negative impacts to data integrity, with the tradeoff of less complicated queries/higher ease of use and increased speed for certain reads.

