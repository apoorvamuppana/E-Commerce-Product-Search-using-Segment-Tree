public class SegmentTree {

    static int[] tree;

    static void build(int arr[], int node, int start, int end) {
        if(start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end)/2;

            build(arr,2*node,start,mid);
            build(arr,2*node+1,mid+1,end);

            tree[node] = Math.min(tree[2*node], tree[2*node+1]);
        }
    }

    static int query(int node,int start,int end,int l,int r) {

        if(r < start || end < l)
            return Integer.MAX_VALUE;

        if(l <= start && end <= r)
            return tree[node];

        int mid = (start + end)/2;

        int p1 = query(2*node,start,mid,l,r);
        int p2 = query(2*node+1,mid+1,end,l,r);

        return Math.min(p1,p2);
    }

    public static void main(String[] args) {

        int prices[] =
        {120,550,300,799,450,1200,680,1750,900,1999};

        int n = prices.length;

        tree = new int[4*n];

        build(prices,1,0,n-1);

        System.out.println("Min Price [2,7] = Rs."
                + query(1,0,n-1,2,7));

        System.out.println("Min Price [0,9] = Rs."
                + query(1,0,n-1,0,9));
    }
}