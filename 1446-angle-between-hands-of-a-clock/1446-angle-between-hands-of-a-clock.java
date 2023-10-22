class Solution {
    public double angleClock(int hour, int minutes) {
        double anglePerMinitue = 360.0 / 60;
        double anglePerHour = 360.0 / 12;
        double angleMinitueInHour = anglePerHour / 60;

        double aHour = hour * anglePerHour + angleMinitueInHour * minutes;
        aHour %= 360.0;
        double aMin = minutes * anglePerMinitue;
        aMin %= 360.0;
        double diff = Math.abs(aHour - aMin);
        return Math.min(diff, 360.0 - diff);
    }
}