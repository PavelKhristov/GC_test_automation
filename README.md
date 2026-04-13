В данном проекте тестируется сайт https://bonigarcia.dev/selenium-webdriver-java/index.html

Примечание:
1. Thread.sleep используется намеренно в некоторых тестах для замедления работы, т.к. без него слишком быстро пробегают тесты. 

Бранчи:
1. 1-simple-UI-Tests:
    - Сделаны простые тесты
      - ui.simpleTests.HomePageTests - Проверяется открытие головной страницы и открытие некоторых страницы в каждом из Chapter
      - ui.simpleTests.WebFormPageTests
      - ui.simpleTests.NavigationPageTests
      - ui.simpleTests.DropdownMenuPageTests
      - ui.simpleTests.MouseOverPageTests
      - ui.simpleTests.DragAndDropPageTests
      - ui.simpleTests.LoadingImagesPageTests
      - ui.simpleTests.InfiniteScrollPageTests
      - ui.simpleTests.ShadowDOMPageTests
      - ui.simpleTests.CookiesPageTests
      - ui.simpleTests.IframesPageTests
      - ui.simpleTests.DialogBoxesPageTests
      - ui.simpleTests.WebStoragePageTests
      - ui.simpleTests.OtherTests - тесты по работе табами и окнами браузера
2. 2-Properties:
   - создание констант, системных переменных и проперти файла
   - использование проперти файлов с использованием библиотеки Owner
   - TestConfig используется в LoginPageTests, TestPropertiesConfig используется в остальных тестах
   - test.properties имеет нулевые задержки для Thread.sleep (для быстрой проверки), остальные имеют не нулевые значения (для наглядности)
3. 3-PageObject
   - добавлен BaseTest для простых тестов
   - добавлены POM: в pages страницы, а в POMTests тесты
   - добавлен алюр
4. 4-PageFactory
   - Добавлены страницы и тесты в пакете PageFactory и переделаны под PF
   - Добавлены POM страницы и тесты для Selenide в пакет Selenide