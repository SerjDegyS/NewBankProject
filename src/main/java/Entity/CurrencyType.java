package Entity;

public enum CurrencyType {
    USD("$"),
    EUR("€"),
    UAH("₴");

    private String description;

    CurrencyType(String description) {
        this.description = description;
    }
}
