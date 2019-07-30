package rw.bk.taxi24.config;

public  class Utility {

    /**
     *
     * @param latitude1 in Degrees
     * @param longitude1 in Degrees
     * @param latitude2 in Degrees
     * @param longitude2 in Degrees
     * @return the pabsolute value of the distance between the 2 coordinates
     */
    public static double CoordDistance(double latitude1, double longitude1, double latitude2, double longitude2)
    {
        latitude1=latitude1*Math.PI/180;
        longitude1=longitude1*Math.PI/180;
        latitude2=latitude2*Math.PI/180;
        longitude2=longitude2*Math.PI/180;

        return Math.abs(6371 * Math.acos(
                Math.sin(latitude1) * Math.sin(latitude2)
                        + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitude2 - longitude1)));
    }
}
