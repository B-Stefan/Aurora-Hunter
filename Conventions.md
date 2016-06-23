# Conventions for Aurora-Forecast-Supreme

## Treat acronyms as words
| Good           | Bad            |
| -------------- | -------------- |
| `CainActivity` | `CAINActivity` |
| `getCustomerId`  | `getCustomerID`  |
| `String url`     | `String URL`     |
| `long id`        | `long ID`        |

## Attributes

#### Public
```java
public int publicAttribute;
```

#### Private
```java
private int mPrivateAttribute;
```

#### Protected
```java
protected int mProtectedAttribute;
```

#### static
```java
private static int sStaticAttribute;
```

#### final
```java
public static final int CONSTANT = 1337;
```

## Resources

### Strings
String names start with a prefix that identifies the section they belong to. For example `registration.email.hint` or `registration.name.hint`. If a string __doesn't belong__ to any section, then you should follow the rules below:


| Prefix             | Description                           |
| -----------------  | --------------------------------------|
| `error.`             | An error message                      |
| `msg.`               | A regular information message         |
| `title.`             | A title, i.e. a dialog title          |
| `action.`            | An action such as "Save" or "Create"  |

### Colors

```xml
<resources>
    <!-- grayscale -->
    <color name="white">#FFFFFF</color>
    <color name="gray_light">#DBDBDB</color>
    <color name="gray">#939393</color>
    <color name="gray_dark">#5F5F5F</color>
    <color name="black"	>#323232</color>

    <!-- basic colors -->
    <color name="green">#27D34D</color>
    <color name="blue">#2A91BD</color>
    <color name="orange">#FF9D2F</color>
    <color name="red">#FF432F</color>
</resources>
```

### Styles and Themes
Unless the rest of resources, style names are written in __UpperCamelCase__.

### ID naming

IDs should be prefixed with the name of the element in lowercase underscore. For example:

| Element            | Prefix            |
| -----------------  | ----------------- |
| `TextView`           | `text_`             |
| `ImageView`          | `image_`            |
| `Button`             | `button_`           |
| `Menu`               | `menu_`             |

Image view example:

```xml
<ImageView
    android:id="@+id/image_profile"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

### XML views

```xml
<TextView
	android:id="@+id/text_view_profile"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content" />
```
Order:

1. View Id
2. Style
3. Layout width and layout height
4. Other layout attributes, sorted alphabetically
5. Remaining attributes, sorted alphabetically


### Drawables

| Asset Type   | Prefix            |		Example               |
|--------------| ------------------|-----------------------------|
| Action bar   | `ab_`             | `ab_stacked.9.png`          |
| Button       | `btn_`	            | `btn_send_pressed.9.png`    |
| Dialog       | `dialog_`         | `dialog_top.9.png`          |
| Divider      | `divider_`        | `divider_horizontal.9.png`  |
| Icon         | `ic_`	            | `ic_star.png`               |
| Menu         | `menu_	`           | `menu_submenu_bg.9.png`     |
| Notification | `notification_`	| `notification_bg.9.png`     |
| Tabs         | `tab_`            | `tab_pressed.9.png`         |

### Icons

| Asset Type                      | Prefix             | Example                      |
| --------------------------------| ----------------   | ---------------------------- |
| Icons                           | `ic_`              | `ic_star.png`                |
| Launcher icons                  | `ic_launcher`      | `ic_launcher_calendar.png`   |
| Menu icons and Action Bar icons | `ic_menu`          | `ic_menu_archive.png`        |
| Status bar icons                | `ic_stat_notify`   | `ic_stat_notify_msg.png`     |
| Tab icons                       | `ic_tab`           | `ic_tab_recent.png`          |
| Dialog icons                    | `ic_dialog`        | `ic_dialog_info.png`         |

### States
| State	       | Suffix          | Example                     |
|--------------|-----------------|-----------------------------|
| Normal       | `_normal`       | `btn_order_normal.9.png`    |
| Pressed      | `_pressed`      | `btn_order_pressed.9.png`   |
| Focused      | `_focused`      | `btn_order_focused.9.png`   |
| Disabled     | `_disabled`     | `btn_order_disabled.9.png`  |
| Selected     | `_selected`     | `btn_order_selected.9.png`  |

### Layout files
| Component        | Class Name             | Layout Name                   |
| ---------------- | ---------------------- | ----------------------------- |
| Activity         | `UserProfileActivity`  | `activity_user_profile.xml`   |
| Fragment         | `SignUpFragment`       | `fragment_sign_up.xml`        |
| Dialog           | `ChangePasswordDialog` | `dialog_change_password.xml`  |
| AdapterView item | ---                    | `item_person.xml`             |
| Partial layout   | ---                    | `partial_stats_bar.xml`       |

## Patterns etc.

### Logtag
```java
public static final String TAG = <Classname>.class.getSimpleName();
```

### Enums
Avoid using Enums. Use final attributes.

### Holder
Use a private static Holder class for View elements.

```java
private static ViewHolder mViewHolder = new ViewHolder();
private static class ViewHolder {
        NumberPicker mNumberPicker;
        TextView mTextView;
        ...
    }
```
