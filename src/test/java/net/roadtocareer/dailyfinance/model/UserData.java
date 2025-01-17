package net.roadtocareer.dailyfinance.model;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/*
 ** 2025, January 14, Tuesday, 11:57 AM
 */
public class UserData {
    private String _id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String gender;
    private boolean termsAccepted;
    private String role;
    private Object profileImage;
    private Object resetPasswordToken;
    private Object resetPasswordExpire;
    private String createdAt;
    private String updatedAt;

    public UserData() {
    }

    public UserData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserData(String firstName, String lastName, String email, String password, String phoneNumber, String address, String gender, boolean termsAccepted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.termsAccepted = termsAccepted;
    }

    public UserData(String _id, String firstName, String lastName, String email, String password, String phoneNumber, String address, String gender, boolean termsAccepted, String role, Object profileImage, Object resetPasswordToken, Object resetPasswordExpire, String createdAt, String updatedAt) {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.termsAccepted = true;
        this.role = "user";
        this.profileImage = null;
        this.resetPasswordToken = null;
        this.resetPasswordExpire = null;
        this.createdAt = new SimpleDateFormat("YYYY-MM-dd").format(new Date().getTime());
        this.updatedAt = new SimpleDateFormat("YYYY-MM-dd").format(new Date().getTime());
    }

    public UserData generator() {
        UserData userData = new UserData();

        Faker faker = new Faker();
        String firstName = faker.name().firstName();

        userData.setFirstName(firstName);
        String lastName = faker.name().lastName();
        userData.setLastName(lastName);
        userData.setEmail(String.format("%s.%s%d@gmail.com",
                firstName.toLowerCase(), lastName.toLowerCase(), new Random().nextInt(100)));
        userData.setPassword("1234");
        userData.setPhoneNumber("012" + generateRandom8Digit());
        userData.setAddress(faker.address().fullAddress());
        userData.setGender(new ArrayList<String>(List.of("Male", "Female"))
                .get(new Random().nextInt(2)));
        userData.setTermsAccepted(this.termsAccepted = true);

        return userData;
    }

//    public UserData update() throws IOException, ParseException {
//        UserData userData = new UserData();
//        JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader("./src/test/resources/user.json"));
//
//        userData.setFirstName(jsonObject.get("firstName").toString() + "_updated");
//        userData.setLastName(jsonObject.get("lastName").toString() + "_updated");
//        userData.setEmail(jsonObject.get("email").toString());
//        userData.setPassword(jsonObject.get("password").toString());
//        userData.setPhoneNumber(jsonObject.get("phoneNumber").toString());
//        userData.setAddress(jsonObject.get("address").toString());
//        userData.setGender(jsonObject.get("gender").toString());
//        userData.setTermsAccepted(this.termsAccepted);
//
//        return userData;
//    }

    /*public void setToJson() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        jsonObject.put("phoneNumber", phoneNumber);
        jsonObject.put("address", address);
        jsonObject.put("gender", gender);

        FileWriter fileWriter = new FileWriter("./src/test/resources/user.json");
        fileWriter.write(jsonObject.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }*/

    /*public UserData getFromJson() throws IOException, ParseException {
        UserData userData = new UserData();

        JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader("./src/test/resources/user.json"));
        userData.setFirstName(jsonObject.get("firstName").toString());
        userData.setLastName(jsonObject.get("lastName").toString());
        userData.setEmail(jsonObject.get("email").toString());
        userData.setPassword(jsonObject.get("password").toString());
        userData.setPhoneNumber(jsonObject.get("phoneNumber").toString());
        userData.setAddress(jsonObject.get("address").toString());
        userData.setGender(jsonObject.get("gender").toString());
        userData.setTermsAccepted(true);

        return userData;
    }*/

    public int generateRandom8Digit() {
        return (int) (Math.random() * (99999999 - 10000000) + 10000000);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isTermsAccepted() {
        return termsAccepted;
    }

    public void setTermsAccepted(boolean termsAccepted) {
        this.termsAccepted = termsAccepted;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Object getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Object profileImage) {
        this.profileImage = profileImage;
    }

    public Object getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(Object resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public Object getResetPasswordExpire() {
        return resetPasswordExpire;
    }

    public void setResetPasswordExpire(Object resetPasswordExpire) {
        this.resetPasswordExpire = resetPasswordExpire;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "UserData{" +
                "id='" + _id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", termsAccepted=" + termsAccepted +
                ", role='" + role + '\'' +
                ", profileImage=" + profileImage +
                ", resetPasswordToken=" + resetPasswordToken +
                ", resetPasswordExpire=" + resetPasswordExpire +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
