class Solution {  
public:  
    /* 
     * param k : description of k 
     * param nums : description of array and index 0 ~ n-1 
     * return: description of return 
     */  
    int kthLargestElement(int k, vector<int> nums) {  
        // write your code here  
        return findKthLargestElement(k,nums,0,nums.size()-1);  
    }  
    int findKthLargestElement(int k,vector<int>& nums,int start,int end)  
    {  
        if(start==end)  
            return nums[start];  
        int index = partition(nums,start,end);  
        if(end-index+1==k)  
            return nums[index];  
        else if(end-index+1>k)  
            return findKthLargestElement(k,nums,index+1,end);  
        else  
            return findKthLargestElement(k-(end-index+1),nums,start,index-1);  
    }  
    int partition(vector<int>&nums,int start,int end)  
    {  
        int x =  (start+end)/2;  
        swap(nums[start],nums[x]);  
        int i = start+1;  
        int j = i;  
        while(i<=end&&j<=end)  
        {  
            if(nums[j]<nums[start])  
            {  
                swap(nums[i],nums[j]);  
                i++;  
            }  
            j++;  
        }  
        swap(nums[start],nums[i-1]);  
        return i-1;  
    }  
};  
