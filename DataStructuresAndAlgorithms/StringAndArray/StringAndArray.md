# String and Array Problems
Some problems are just simple String and Array problems that can be solved
using basic understanding of these structures and how they work. 

## Examples

### Best Time to Buy and Sell Stock

You are given an array, prices, where prices[i] is the cost of a stock on a
given day. You want to maximize your profit by choosing to buy and sell on the
right days. 

Return the maximum profit you can make. 

Example: [7,1,5,3,6,4]
Output: 5 (Buy on 2(1) sell on day 5(6))

#### Solution
We will track the smallest number and the highest profit as we move through the
array. Because we are moving through in order of days we can always look for
the smallest and calculate the profit using the current number in the array.

```java
    public int maxProfit(int[] prices) {
       int minValue = Integer.MAX_VALUE;
       int profit = 0;
       for (int i = 0; i < prices.length; i++) {
           minValue = Math.min(minValue, prices[i]);
           profit = Math.max(profit, prices[i] - minValue);
       }
       return profit;
    }
```
