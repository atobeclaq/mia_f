const firstWillWin = function (n) {
    if (n<=0){return false;}
    if (n==1||n%3!=0)
    {
        return true;
    }
    return false;
}
