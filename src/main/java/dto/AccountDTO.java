package dto;

import java.sql.Date;

/**
 * AccountDTO represents a data transfer object for the 'Account' entity. This
 * class is used to transfer account data between the View, Controller, and DAO
 * layers of the ReadTopia application, and reflects the structure of the
 * Account table in the database.
 */
public class AccountDTO {

    // ===== Account Fields =====
    private String username;     // Unique identifier for the account
    private String password;     // Account password (stored in hashed format in DB)
    private String firstName;    // User's first name
    private String lastName;     // User's last name
    private Date dob;            // Date of birth
    private String email;        // Email address used for contact and verification
    private String phone;        // User's phone number
    private int role;            // Role: 0 = admin/staff, 1 = customer
    private String address;      // User's address
    private int sex;             // Gender: 0 = female, 1 = male
    private int accStatus;       // Account status: 0 = inactive, 1 = active
    private String code;         // One-time code for password reset or verification

    // ===== Constructors =====
    /**
     * Default constructor. Creates an empty AccountDTO object.
     */
    public AccountDTO() {
    }

    /**
     * Parameterized constructor that initializes all account fields. Useful
     * when retrieving full account details from database.
     *
     * @param username Account username
     * @param password Account password
     * @param firstName First name
     * @param lastName Last name
     * @param dob Date of birth
     * @param email Email address
     * @param phone Phone number
     * @param role User role
     * @param address Address
     * @param sex Gender
     * @param accStatus Account status
     * @param code Verification or reset code
     */
    public AccountDTO(String username, String password, String firstName, String lastName,
            Date dob, String email, String phone, int role, String address,
            int sex, int accStatus, String code) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.address = address;
        this.sex = sex;
        this.accStatus = accStatus;
        this.code = code;
    }

    /**
     * Copy constructor. Creates a duplicate of an existing AccountDTO object.
     *
     * @param other Another AccountDTO object
     */
    public AccountDTO(AccountDTO other) {
        this.username = other.username;
        this.password = other.password;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.dob = other.dob;
        this.email = other.email;
        this.phone = other.phone;
        this.role = other.role;
        this.address = other.address;
        this.sex = other.sex;
        this.accStatus = other.accStatus;
        this.code = other.code;
    }

    // ===== Getters and Setters =====
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(int accStatus) {
        this.accStatus = accStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // ===== Utility Methods =====
    /**
     * Combines first and last name into a single full name string.
     *
     * @return Full name in the format "FirstName LastName"
     */
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Returns a readable string containing the main account details, excluding
     * sensitive fields like password or code.
     *
     * @return A string describing the account information
     */
    @Override
    public String toString() {
        return "AccountDTO{"
                + "username='" + username + '\''
                + ", fullName='" + getFullName() + '\''
                + ", dob=" + dob
                + ", email='" + email + '\''
                + ", phone='" + phone + '\''
                + ", role=" + role
                + ", sex=" + sex
                + ", accStatus=" + accStatus
                + '}';
    }
}
