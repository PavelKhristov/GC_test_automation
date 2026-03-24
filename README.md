В данном проекте тестируется сайт https://bonigarcia.dev/selenium-webdriver-java/index.html

Примечание:
1. Thread.sleep используется намеренно в некоторых тестах для замедления работы, т.к. без него слишком быстро пробегают тесты. 

Бранчи:
1. 1-simple-UI-Tests:
    - Сделаны простые тесты
      - ui.HomePageTests - Проверяется открытие головной страницы и открытие некоторых страницы в каждом из Chapter
      - ui.WebFormPageTests
      - NavigationPageTests
      - ui.DropdownMenuPageTests
      - ui.MouseOverPageTests
      - ui.DragAndDropPageTests
      - ui.LoadingImagesPageTests
      - ui.InfiniteScrollPageTests
      - ui.ShadowDOMPageTests
      - ui.CookiesPageTests
      - ui.IframesPageTests
      - ui.DialogBoxesPageTests
      - ui.WebStoragePageTests
      - ui.OtherTests - тесты по работе табами и окнами браузера
2. 2-Properties:
   - создание констант, системных переменных и проперти файла