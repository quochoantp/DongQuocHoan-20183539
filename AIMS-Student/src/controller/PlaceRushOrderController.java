package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;



/**
 * Class thực hiện controls các hàm liên quan đến giao hàng nhanh
 * @author Hoan - 20183539
 */
public class PlaceRushOrderController {

    /**
     * Specify provinces where support rush order
     */
    public static List<String> PROVINCES_SUPPORT_RUSH_ODER = List.of("Ha Noi");

    /**
     * Specify list id of media that support rush order
     * Set only media id = 200 for testing
     */
    public static List<Integer> MEDIA_IDS_SUPPORT_RUSH_ORDER = List.of(200);

    /**
     * Just for logging purpose
     */
    private static final Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());

    public static final String RECEIVE_TIME_FORMATTER = "dd-MM-yyyy HH:mm";

    /**
     * Method checks if user's location supports rush order or not
     * @param location User's province
     */
    public boolean isLocationSupportRushOrder(String location) {
        if (location == null) {
            return false;
        }
        if (PROVINCES_SUPPORT_RUSH_ODER.contains(location)) {
            return true;
        }
        return false;
    }

    /**
     * Method checks if user's media supports rush order or not
     * @param mediaID Cart's media id
     */
    public boolean isItemsSupportRushOrder(int mediaID) {
        if (MEDIA_IDS_SUPPORT_RUSH_ORDER.contains(mediaID)) {
            return true;
        }
        return false;
    }

    /**
     * Method checks user's info support rush order or not
     * @param location User's province
     * @param mediaID Cart's media id
     */
    public boolean isSupportRushOrder(String location, int mediaID) {
        return isLocationSupportRushOrder(location) && isItemsSupportRushOrder(mediaID);
    }

    /**
     * Method validates user's receive time
     * @param time User's receive time
     */
    public boolean validateReceiveTime(String time) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RECEIVE_TIME_FORMATTER);
            LocalDateTime start = LocalDateTime.of(2000, 1, 1, 0, 0);
            LocalDateTime end = LocalDateTime.of(2100, 1, 1,0,0);
            LocalDateTime timeInput = LocalDateTime.parse(time, formatter);
            if(timeInput.isAfter(start)&&timeInput.isBefore(end)) {           
                	return true;
            } 
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method validates user's rush order info
     * @param info User's rush order info
     */
    public boolean validateRushOrderInfo(String info) {
        return validateBasicString(info);
    }

    /**
     * Method validates user's rush order instruction
     * @param instruction User's rush order instruction
     */
    public boolean validateRushOrderInstruction(String instruction) {
        return validateBasicString(instruction);
    }

    @SuppressWarnings("deprecation")
	private boolean validateBasicString(String info) {
        if (info == null || info.isEmpty()) {
            return false;
        }

        boolean isValid = true;
        for (char ch : info.toCharArray()) {
            if ( Character.isSpace(ch) ) {
                continue;
            }
            if ( Character.isDigit(ch) ) {
            	continue;
            }
            if ( Character.isLetter(ch) ) {
            	continue;
            }
            isValid = false;
            break;
        }

        return isValid;
    }
}
