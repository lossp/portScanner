package portscan;

public class IpAssertion {
    /**
     * check the ip correctness
     * @param ipAddress ip address
     * @return return true if the ip address is legit, return false otherwise
     */
    public static boolean ipCheck(String ipAddress) {
        if (ipAddress != null && !ipAddress.isEmpty()) {
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            return ipAddress.matches(regex);
        }
        return false;
    }
}
