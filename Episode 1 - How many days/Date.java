class Date {

    private final int[] cumulativeDays = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
    private String date;
    private int day;
    private int month; //January index = 0, December index = 11.
    private int year;

    /**
    * Constructor for Date class. Initializes this object's date, day, month and year parameters.
    * @param date - String of the date in DDMMYYYY format.
    */
    public Date(String date) {
        this.date = date;
        this.day = Integer.parseInt(date.substring(0, 2));
        this.month = Integer.parseInt(date.substring(2, 4));
        this.month--;
        this.year = Integer.parseInt(date.substring(4, 8));
    }

    /**
    * Returns the date String of this Date object.
    * @return String of the date in DDMMYYYY format.
    */
    public String getDate() {
        return this.date;
    }

    /**
    * Counts the number of days since 00/00/0000.
    * @return number of days since 00/00/0000.
    */
    public int daysSinceBC() {
        //Add the years, first assume that every year is non leap, then add for each leap year.
        int count = this.year * 365;
        count += (this.year / 4) - (this.year / 100) + (this.year / 400);

        //Add the months
        count += cumulativeDays[this.month];
        if (this.month <= 1 && isLeapYear(this.year)) {
            count--;
        }

        //Add the days
        count += this.day;
        return count;
    }

    /**
    * Counts the difference in number of days between this Date object and input Date object.
    * @param otherDate - Another Date object to compare this Date object to.
    * @return number of days difference between this Date and otherDate.
    */
    public int daysAway(Date otherDate) {
        return Math.abs(daysSinceBC() - otherDate.daysSinceBC());
    }

    /**
    * Driver method to check if year is a leap year.
    * @param year - The year to check for.
    * @return true if year is leap year, false otherwise.
    */
    public boolean isLeapYear(int year) {
        boolean is4thYear = year % 4 == 0;
        boolean is100thYear = year % 100 == 0;
        boolean is400thYear = year % 400 == 0;

        // Every four years, but not 100th years, except 400th years
        return is4thYear && (!is100thYear || is400thYear);
    }
}
