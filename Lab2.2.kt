import Currency.EUR
import Currency.UAH
import Currency.USD

data class Trader(val name: String, val city: String)
data class Transaction(val trader: Trader, val year: Int, val month: Int, val value: Int, val currency: Currency)

enum class Currency {
    UAH, USD, EUR
}

fun main() {
    val raoul = Trader("Raoul", "Cambridge")
    val mario = Trader("Mario", "Milan")
    val alan = Trader("Alan", "Cambridge")
    val brian = Trader("Brian", "Cambridge")
    val transactions = listOf(
        Transaction(brian, 2011, 12, 300, UAH),
        Transaction(raoul, 2012, 10, 1000, UAH),
        Transaction(raoul, 2011, 11, 400, USD),
        Transaction(mario, 2012, 9, 710, UAH),
        Transaction(mario, 2012, 7, 700, USD),
        Transaction(alan, 2012, 4, 950, EUR)
    )

    // 1. Знайти усі транзакції за 2011 рік і посортувати за вартістю (від малого до високого).
    val transactions2011 = transactions.filter { it.year == 2011 }
        .sortedBy { it.value }
    println("1. Транзакції за 2011 рік, відсортовані за вартістю: $transactions2011")

    // 2. У яких унікальних містах працюють трейдери?
    val cities = transactions.map { it.trader.city }.toSet()
    println("2. Унікальні міста трейдерів: $cities")

    // 3. Знайдіть усіх трейдерів із Кембриджа та відсортуйте їх за назвою.
    val tradersInCambridge = transactions.filter { it.trader.city == "Cambridge" }
        .map { it.trader.name }
        .sorted()
    println("3. Трейдери з Кембриджа, відсортовані за назвою: $tradersInCambridge")

    // 4. Поверніть рядок імен усіх трейдерів, відсортованих за алфавітом.
    val traderNames = transactions.map { it.trader.name }.sorted()
    println("4. Імена трейдерів, відсортовані за алфавітом: $traderNames")

    // 5. Чи є трейдери в Мілані?
    val hasTradersInMilan = transactions.any { it.trader.city == "Milan" }
    println("5. Чи є трейдери в Мілані? $hasTradersInMilan")

    // 6. Виведіть у консоль всі значення транзакцій від трейдерів, які проживають у Кембриджі.
    val transactionsInCambridge = transactions.filter { it.trader.city == "Cambridge" }
    println("6. Транзакції трейдерів з Кембриджа: $transactionsInCambridge")

    // 7. Знайдіть транзакцію з найбільшою вартістю.
    val maxTransaction = transactions.maxByOrNull { it.value }
    println("7. Транзакція з найбільшою вартістю: $maxTransaction")

    // 8. Згрупуйте всі транзакції за валютою.
    val groupedByCurrency = transactions.groupBy { it.currency }
    println("8. Транзакції, згруповані за валютою: $groupedByCurrency")

    // 9. Знайдіть суму транзакцій у гривнях.
    val sumInUAH = transactions.filter { it.currency == UAH }
        .sumOf { it.value }
    println("9. Сума транзакцій у гривнях: $sumInUAH")

    // 10. Створіть рядок, у якому буде виведена послідовність транзакцій відсортована за датою.
    val transactionString = transactions.sortedWith(compareBy({ it.year }, { it.month }))
        .mapIndexed { index, transaction ->
            "${index + 1}. ${transaction.month}-${transaction.year}: ${transaction.value} ${transaction.currency}"
        }
        .joinToString(" -> ")
    println("10. Послідовність транзакцій, відсортованих за датою: $transactionString")
}