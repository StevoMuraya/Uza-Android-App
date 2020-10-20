-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 20, 2020 at 01:25 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uza`
--

-- --------------------------------------------------------

--
-- Table structure for table `app_users`
--

CREATE TABLE `app_users` (
  `user_id` int(20) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `password` text NOT NULL,
  `date_time_reg` date NOT NULL,
  `county` varchar(20) NOT NULL,
  `profile_pic` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cart_id` int(20) NOT NULL,
  `product_id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `quantity` int(7) NOT NULL DEFAULT '1',
  `original_price` int(7) NOT NULL,
  `price` int(7) NOT NULL,
  `seller_name` varchar(50) NOT NULL,
  `rating` int(1) NOT NULL,
  `percentage_strike` int(2) NOT NULL,
  `product_image` text NOT NULL,
  `date_added` date NOT NULL,
  `purchase` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `payment_amount` int(7) NOT NULL,
  `payment_method` varchar(20) NOT NULL,
  `payment_code` varchar(16) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(20) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `quantity` int(5) NOT NULL,
  `original_price` int(7) NOT NULL,
  `price` int(7) NOT NULL,
  `seller_name` varchar(50) NOT NULL,
  `rating` int(1) NOT NULL,
  `percentage_strike` int(2) NOT NULL,
  `product_image` text NOT NULL,
  `date_added` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_name`, `category`, `description`, `quantity`, `original_price`, `price`, `seller_name`, `rating`, `percentage_strike`, `product_image`, `date_added`) VALUES
(4, 'Gold Beer - 330ml (24 Pcs).', 'Food & Groceries', 'Some description for Gold Beer - 330ml (24 Pcs).. You won''t regret buying this product', 105, 3500, 3816, 'Ruhr Gold ', 2, 6, 'https://ke.jumia.is/HsegWyZjhvgAXgU5iiN3BnW1Too=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/07/234682/1.jpg', '2019-10-16'),
(5, 'Gold Beer - 330ml (24 Pcs).', 'Food & Groceries', 'Some description for Gold Beer - 330ml (24 Pcs).. You won''t regret buying this product', 107, 3500, 3816, 'Ruhr Gold ', 2, 6, 'https://ke.jumia.is/HsegWyZjhvgAXgU5iiN3BnW1Too=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/07/234682/1.jpg?5224', '2019-10-16'),
(6, 'Premium White Sugar - 2kg', 'Food & Groceries', 'Some description for Premium White Sugar - 2kg. You won''t regret buying this product', 161, 230, 273, 'Kabras ', 5, 46, 'https://ke.jumia.is/NpFB9A1P13n2Kx93E-8GQgyc8fM=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/12/175151/1.jpg?6585', '2020-03-10'),
(7, 'Freshi Bundle (Express Delivery)', 'Food & Groceries', 'Some description for Freshi Bundle (Express Delivery). You won''t regret buying this product', 12, 3811, 4401, 'Twiga ', 1, 55, 'https://ke.jumia.is/jiFl7eikl1MZR8yT6QWix6oLdxY=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/92/390982/1.jpg?1019', '2019-02-27'),
(8, 'Dairy Top Milk 500ml-A  Pack of 12 Pieces', 'Food & Groceries', 'Some description for Dairy Top Milk 500ml-A  Pack of 12 Pieces. You won''t regret buying this product', 255, 600, 711, 'Dairy ', 1, 19, 'https://ke.jumia.is/CwZiHXmPbrrBKG634Af5MQ-zGTs=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/73/557752/1.jpg?7287', '2019-06-28'),
(9, 'Water - 18.5 Litres - Disposable Bottle', 'Food & Groceries', 'Some description for Water - 18.5 Litres - Disposable Bottle. You won''t regret buying this product', 270, 500, 521, 'Aquamist ', 2, 47, 'https://ke.jumia.is/wlh6t99gCqIL-RYBExUrOozbHBU=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/17/52428/1.jpg?5457', '2020-02-27'),
(10, 'Margarine - 1kg', 'Food & Groceries', 'Some description for Margarine - 1kg. You won''t regret buying this product', 199, 330, 345, 'Blue Band ', 2, 45, 'https://ke.jumia.is/HcAteGJeb7HqJFy38A2E6N9iCYc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/60/855531/1.jpg?8668', '2019-08-13'),
(11, 'Margarine - 1kg', 'Food & Groceries', 'Some description for Margarine - 1kg. You won''t regret buying this product', 199, 330, 345, 'Blue Band ', 2, 45, 'https://ke.jumia.is/HcAteGJeb7HqJFy38A2E6N9iCYc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/60/855531/1.jpg?8668', '2019-08-13'),
(12, 'Chakula Box (One Click, One Delivery)', 'Food & Groceries', 'Some description for Chakula Box (One Click, One Delivery). You won''t regret buying this product', 47, 330, 345, 'Blue Band ', 2, 45, 'https://ke.jumia.is/HcAteGJeb7HqJFy38A2E6N9iCYc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/60/855531/1.jpg?8668', '2019-08-13'),
(13, 'All-Purpose Fortified Wheat Flour - 2Kg', 'Food & Groceries', 'Some description for All-Purpose Fortified Wheat Flour - 2Kg. You won''t regret buying this product', 58, 330, 345, 'Blue Band ', 2, 45, 'https://ke.jumia.is/HcAteGJeb7HqJFy38A2E6N9iCYc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/60/855531/1.jpg?8668', '2019-08-13'),
(14, 'Chapati Fortified Wheat Flour - 2Kg', 'Food & Groceries', 'Some description for Chapati Fortified Wheat Flour - 2Kg. You won''t regret buying this product', 134, 330, 345, 'Blue Band ', 2, 45, 'https://ke.jumia.is/HcAteGJeb7HqJFy38A2E6N9iCYc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/60/855531/1.jpg?8668', '2019-08-13'),
(15, 'Hand Washing Powder Extra Fresh - 3.5kg', 'Food & Groceries', 'Some description for Hand Washing Powder Extra Fresh - 3.5kg. You won''t regret buying this product', 239, 950, 1023, 'Omo ', 1, 25, 'https://ke.jumia.is/TYnrPKVNY3f0EfcxYzxZn-MmWJo=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/06/389352/1.jpg?1125', '2019-07-10'),
(16, 'Maize Meal  - 2kg', 'Food & Groceries', 'Some description for Maize Meal  - 2kg. You won''t regret buying this product', 265, 950, 1023, 'Omo ', 1, 25, 'https://ke.jumia.is/TYnrPKVNY3f0EfcxYzxZn-MmWJo=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/06/389352/1.jpg?1125', '2019-07-10'),
(17, 'Maize Meal  - 2kg', 'Food & Groceries', 'Some description for Maize Meal  - 2kg. You won''t regret buying this product', 265, 950, 1023, 'Omo ', 1, 25, 'https://ke.jumia.is/TYnrPKVNY3f0EfcxYzxZn-MmWJo=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/06/389352/1.jpg?1125', '2019-07-10'),
(18, 'Drinking Water - 18.9 Litres - Disposable Bottle', 'Food & Groceries', 'Some description for Drinking Water - 18.9 Litres - Disposable Bottle. You won''t regret buying this product', 146, 550, 576, 'Wetlands Springs ', 1, 7, 'https://ke.jumia.is/iBFp-V76jqgfgve0Fp1k96Q2HoI=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/59/102342/1.jpg?2293', '2019-03-07'),
(19, 'Cream Bar Soap - 1kg', 'Food & Groceries', 'Some description for Cream Bar Soap - 1kg. You won''t regret buying this product', 107, 155, 185, 'Menengai ', 5, 27, 'https://ke.jumia.is/gdq68Dqh6uwQCWeqCqYdrS4iOQ4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/64/515041/1.jpg?6665', '2020-03-11'),
(20, 'Noodles - Chicken Flavour - 20 Pack', 'Food & Groceries', 'Some description for Noodles - Chicken Flavour - 20 Pack. You won''t regret buying this product', 236, 600, 645, 'Indomie ', 5, 58, 'https://ke.jumia.is/vwozWgdAdcjPtYkuiIn2muagO3A=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/76/09848/1.jpg?1100', '2019-11-17'),
(21, 'Self-Raising Fortified Wheat Flour - 2kg', 'Food & Groceries', 'Some description for Self-Raising Fortified Wheat Flour - 2kg. You won''t regret buying this product', 272, 126, 135, 'Exe ', 5, 18, 'https://ke.jumia.is/EcMZeauDy2EBN9LePun8nQO6bcY=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/69/800721/1.jpg?3885', '2020-01-03'),
(22, '2 Ply Wrapped Printed Toilet Tissue - 10 Pack', 'Food & Groceries', 'Some description for 2 Ply Wrapped Printed Toilet Tissue - 10 Pack. You won''t regret buying this product', 245, 245, 282, ' ', 1, 8, 'https://ke.jumia.is/PdEPKxeJ78tWXo37i6Y9iDoOv_w=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/29/574352/1.jpg?7927', '2020-03-08'),
(23, '2 Ply Wrapped Printed Toilet Tissue - 10 Pack', 'Food & Groceries', 'Some description for 2 Ply Wrapped Printed Toilet Tissue - 10 Pack. You won''t regret buying this product', 245, 245, 282, ' ', 1, 8, 'https://ke.jumia.is/PdEPKxeJ78tWXo37i6Y9iDoOv_w=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/29/574352/1.jpg?7927', '2020-03-08'),
(24, 'Pasta Spaghetti - 1Kg', 'Food & Groceries', 'Some description for Pasta Spaghetti - 1Kg. You won''t regret buying this product', 149, 245, 282, ' ', 1, 8, 'https://ke.jumia.is/PdEPKxeJ78tWXo37i6Y9iDoOv_w=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/29/574352/1.jpg?7927', '2020-03-08'),
(25, 'Cooking Oil - 3 Litres', 'Food & Groceries', 'Some description for Cooking Oil - 3 Litres. You won''t regret buying this product', 130, 506, 551, 'Top Fry ', 3, 27, 'https://ke.jumia.is/vvCUdzbsQZ8UcRlkXQp85KzTy2g=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/38/005942/1.jpg?9068', '2019-02-01'),
(26, 'Sugar - 2kg', 'Food & Groceries', 'Some description for Sugar - 2kg. You won''t regret buying this product', 113, 206, 216, 'Nutrameal ', 1, 60, 'https://ke.jumia.is/cwKo3WDtMaeZmH3INdlnmV-mF-c=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/54/699621/1.jpg?1185', '2019-07-04'),
(27, 'Stock Cube Beef Seasoning 40x4g.', 'Food & Groceries', 'Some description for Stock Cube Beef Seasoning 40x4g.. You won''t regret buying this product', 169, 100, 104, 'Royco ', 2, 18, 'https://ke.jumia.is/UQvAvfDQgjdINkwjQH2GIB363I8=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/39/08221/1.jpg?1725', '2019-11-16'),
(28, 'Cooking Oil - 5 Litres', 'Food & Groceries', 'Some description for Cooking Oil - 5 Litres. You won''t regret buying this product', 186, 800, 881, 'Top Fry ', 4, 10, 'https://ke.jumia.is/4y7z-DkfDaLPOgMb5wZSzgNhz9A=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/47/869952/1.jpg?9067', '2019-07-18'),
(29, 'Cooking Oil - 5 Litres', 'Food & Groceries', 'Some description for Cooking Oil - 5 Litres. You won''t regret buying this product', 186, 800, 881, 'Top Fry ', 4, 10, 'https://ke.jumia.is/4y7z-DkfDaLPOgMb5wZSzgNhz9A=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/47/869952/1.jpg?9067', '2019-07-18'),
(30, 'Natural Wholesome Sugar - 1Kg', 'Food & Groceries', 'Some description for Natural Wholesome Sugar - 1Kg. You won''t regret buying this product', 238, 99, 108, 'Nutrameal ', 2, 39, 'https://ke.jumia.is/rLxIS-xSk2LEFSG5zf8gU1eZXMs=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/27/936721/1.jpg?1083', '2019-09-15'),
(31, 'Assorted Jar', 'Food & Groceries', 'Some description for Assorted Jar. You won''t regret buying this product', 204, 400, 460, 'Sunveat ', 5, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(32, 'Maize Meal - 2Kg', 'Food & Groceries', 'Some description for Maize Meal - 2Kg. You won''t regret buying this product', 274, 400, 460, 'Sunveat ', 5, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(33, 'Household Matches 300''s -1 Pack', 'Food & Groceries', 'Some description for Household Matches 300''s -1 Pack. You won''t regret buying this product', 112, 400, 460, 'Sunveat ', 5, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(34, 'Sugar - 1kg', 'Food & Groceries', 'Some description for Sugar - 1kg. You won''t regret buying this product', 60, 400, 460, 'Sunveat ', 5, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(35, 'Sugar - 1kg', 'Food & Groceries', 'Some description for Sugar - 1kg. You won''t regret buying this product', 60, 400, 460, 'Sunveat ', 5, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(36, 'Margarine - 1kg Blue Band', 'Food & Groceries', 'Some description for Margarine - 1kg Blue Band. You won''t regret buying this product', 79, 400, 460, 'Sunveat ', 5, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(37, 'Uht Fino Carton 500ml', 'Food & Groceries', 'Some description for Uht Fino Carton 500ml. You won''t regret buying this product', 24, 400, 460, 'Sunveat ', 5, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(38, 'Salt - 2 Kg', 'Food & Groceries', 'Some description for Salt - 2 Kg. You won''t regret buying this product', 209, 400, 460, 'Sunveat ', 5, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(39, 'Baby Wipes Sensitive 70''s', 'Food & Groceries', 'Some description for Baby Wipes Sensitive 70''s. You won''t regret buying this product', 289, 99, 111, 'Sleepy ', 3, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(40, 'Maize Meal Fortified With Vitamins And Minerals - ', 'Food & Groceries', 'Some description for Maize Meal Fortified With Vitamins And Minerals - 10 Kg. You won''t regret buying this product', 61, 99, 111, 'Sleepy ', 3, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(41, 'Maize Meal Fortified With Vitamins And Minerals - ', 'Food & Groceries', 'Some description for Maize Meal Fortified With Vitamins And Minerals - 10 Kg. You won''t regret buying this product', 61, 99, 111, 'Sleepy ', 3, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(42, 'Mchuzi Mix Beef Scht - 200g', 'Food & Groceries', 'Some description for Mchuzi Mix Beef Scht - 200g. You won''t regret buying this product', 274, 99, 111, 'Sleepy ', 3, 37, 'https://ke.jumia.is/aunrTxGL5xPkKFFTJHSEaVQB5g4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/49/060772/1.jpg?7326', '2019-02-24'),
(43, 'Pink 5kg Basmati Super Kernal + Free 2kg', 'Food & Groceries', 'Some description for Pink 5kg Basmati Super Kernal + Free 2kg. You won''t regret buying this product', 46, 1060, 1106, 'Riza ', 1, 8, 'https://ke.jumia.is/Qbw-QFXtASMPGpzLk-FzKCY0HiM=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/74/234082/1.jpg?9719', '2019-10-21'),
(44, '2 Ply Premium Unwrapped White Toilet Tissue - 10 P', 'Food & Groceries', 'Some description for 2 Ply Premium Unwrapped White Toilet Tissue - 10 Pack. You won''t regret buying this product', 154, 367, 397, 'Velvex ', 1, 36, 'https://ke.jumia.is/zWp_wmoE78oilaCjUISy7S-6aw8=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/16/7812/1.jpg?0942', '2020-03-11'),
(45, 'Pekee 2 Ply Unwrapped White Toilet Tissue - 10 Pac', 'Food & Groceries', 'Some description for Pekee 2 Ply Unwrapped White Toilet Tissue - 10 Pack. You won''t regret buying this product', 19, 195, 226, 'Dawn ', 5, 56, 'https://ke.jumia.is/ppDqYy5D4cC0liGosBWDFBCelIU=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/82/417652/1.jpg?9366', '2019-05-27'),
(46, 'Unga Mandazi Flour - 2kg', 'Food & Groceries', 'Some description for Unga Mandazi Flour - 2kg. You won''t regret buying this product', 172, 126, 133, 'Exe ', 4, 0, 'https://ke.jumia.is/-iG7_9Mx9BwXl8s1FiQXaO8SgMY=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/48/400721/1.jpg?3886', '2019-05-29'),
(47, 'Unga Mandazi Flour - 2kg', 'Food & Groceries', 'Some description for Unga Mandazi Flour - 2kg. You won''t regret buying this product', 172, 126, 133, 'Exe ', 4, 0, 'https://ke.jumia.is/-iG7_9Mx9BwXl8s1FiQXaO8SgMY=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/48/400721/1.jpg?3886', '2019-05-29'),
(48, 'Instant Dry Yeast - 100g', 'Food & Groceries', 'Some description for Instant Dry Yeast - 100g. You won''t regret buying this product', 32, 126, 133, 'Exe ', 4, 0, 'https://ke.jumia.is/-iG7_9Mx9BwXl8s1FiQXaO8SgMY=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/48/400721/1.jpg?3886', '2019-05-29'),
(49, 'Peanut Butter 200g', 'Food & Groceries', 'Some description for Peanut Butter 200g. You won''t regret buying this product', 166, 126, 133, 'Exe ', 4, 0, 'https://ke.jumia.is/-iG7_9Mx9BwXl8s1FiQXaO8SgMY=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/48/400721/1.jpg?3886', '2019-05-29'),
(50, 'Wholegrain Cereal - 900g', 'Food & Groceries', 'Some description for Wholegrain Cereal - 900g. You won''t regret buying this product', 176, 126, 133, 'Exe ', 4, 0, 'https://ke.jumia.is/-iG7_9Mx9BwXl8s1FiQXaO8SgMY=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/48/400721/1.jpg?3886', '2019-05-29'),
(51, 'Premium Unwrapped 2 Ply - 10 Pack White', 'Food & Groceries', 'Some description for Premium Unwrapped 2 Ply - 10 Pack White. You won''t regret buying this product', 268, 250, 261, 'SIFA ', 4, 15, 'https://ke.jumia.is/X5Kuw1wcleTcfU5SY7G5U6eL-5U=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/88/776382/1.jpg?8289', '2019-03-26'),
(52, 'Note 7, 6.1 Inches, 1GB + 16GB (Dual SIM) - Twilig', 'Phones & Tabs', 'Some description for Note 7, 6.1 Inches, 1GB + 16GB (Dual SIM) - Twilight. You won''t regret buying this product', 125, 7999, 8028, 'Ulefone ', 3, 50, 'https://ke.jumia.is/tulcsAgTGKDdBXIIOU9U9hPT8mc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/77/658362/1.jpg?0725', '2019-06-06'),
(53, 'Redmi Note 8, 6.3" FHD, 4GB RAM - 64GB, 48MP, 4G -', 'Phones & Tabs', 'Some description for Redmi Note 8, 6.3" FHD, 4GB RAM - 64GB, 48MP, 4G - Black. You won''t regret buying this product', 172, 21599, 25896, 'XIAOMI ', 5, 24, 'https://ke.jumia.is/tulcsAgTGKDdBXIIOU9U9hPT8mc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/77/658362/1.jpg?0725', '2019-06-06'),
(54, 'K501 GSM Mobile Phone (Dual SIM), Black..', 'Phones & Tabs', 'Some description for K501 GSM Mobile Phone (Dual SIM), Black... You won''t regret buying this product', 35, 1299, 1393, 'X AGE ', 1, 19, 'https://ke.jumia.is/7oEGGTLzzNIH-WLBnA12PW38vUE=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/38/802452/1.jpg?4135', '2019-01-16'),
(55, 'Redmi 8 6.22" HD+ 4GB + 64GB 12MP 4G LTE Smartphon', 'Phones & Tabs', 'Some description for Redmi 8 6.22" HD+ 4GB + 64GB 12MP 4G LTE Smartphone - Black. You won''t regret buying this product', 179, 15499, 18312, 'XIAOMI ', 1, 55, 'https://ke.jumia.is/7oEGGTLzzNIH-WLBnA12PW38vUE=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/38/802452/1.jpg?4135', '2019-01-16'),
(56, 'Redmi 8 6.22" HD+ 4GB + 64GB 12MP 4G LTE Smartphon', 'Phones & Tabs', 'Some description for Redmi 8 6.22" HD+ 4GB + 64GB 12MP 4G LTE Smartphone - Black. You won''t regret buying this product', 179, 15499, 18312, 'XIAOMI ', 1, 55, 'https://ke.jumia.is/7oEGGTLzzNIH-WLBnA12PW38vUE=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/38/802452/1.jpg?4135', '2019-01-16'),
(57, 'Redmi Note 8, 6.3", FHD, 4 GB + 64 GB (Dual SIM) -', 'Phones & Tabs', 'Some description for Redmi Note 8, 6.3", FHD, 4 GB + 64 GB (Dual SIM) - Blue. You won''t regret buying this product', 135, 21599, 24077, 'XIAOMI ', 1, 19, 'https://ke.jumia.is/7oEGGTLzzNIH-WLBnA12PW38vUE=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/38/802452/1.jpg?4135', '2019-01-16'),
(58, 'Note 7 - 6.1"- 1GB+16GB - 8MP+2MP+2MP - DUAL SIM -', 'Phones & Tabs', 'Some description for Note 7 - 6.1"- 1GB+16GB - 8MP+2MP+2MP - DUAL SIM - Green. You won''t regret buying this product', 158, 7999, 9359, 'Ulefone ', 5, 38, 'https://ke.jumia.is/7oEGGTLzzNIH-WLBnA12PW38vUE=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/38/802452/1.jpg?4135', '2019-01-16'),
(59, 'Galaxy A30s, 6.4", 64 GB + 4 GB (Dual SIM) - Black', 'Phones & Tabs', 'Some description for Galaxy A30s, 6.4", 64 GB + 4 GB (Dual SIM) - Black. You won''t regret buying this product', 146, 23999, 28292, 'Samsung ', 3, 26, 'https://ke.jumia.is/7oEGGTLzzNIH-WLBnA12PW38vUE=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/38/802452/1.jpg?4135', '2019-01-16'),
(60, 'IPhone 5 Smartphone,4'''' Inches,16GB Rom+1GB Ram,8M', 'Phones & Tabs', 'Some description for IPhone 5 Smartphone,4'''' Inches,16GB Rom+1GB Ram,8MP,Single Sim,Refurbished. You won''t regret buying this product', 229, 12980, 14827, 'Apple ', 2, 21, 'https://ke.jumia.is/7oEGGTLzzNIH-WLBnA12PW38vUE=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/38/802452/1.jpg?4135', '2019-01-16'),
(61, 'Ta M165 earphone headset headphone V4.0 wireless h', 'Phones & Tabs', 'Some description for Ta M165 earphone headset headphone V4.0 wireless handfree-black. You won''t regret buying this product', 114, 554, 637, 'Generic ', 5, 2, 'https://ke.jumia.is/0XfRxD_udDqSG_zj64o18BQ4l0o=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/93/747891/1.jpg?6019', '2019-06-11'),
(62, 'Ta M165 earphone headset headphone V4.0 wireless h', 'Phones & Tabs', 'Some description for Ta M165 earphone headset headphone V4.0 wireless handfree-black. You won''t regret buying this product', 114, 554, 637, 'Generic ', 5, 2, 'https://ke.jumia.is/0XfRxD_udDqSG_zj64o18BQ4l0o=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/93/747891/1.jpg?6019', '2019-06-11'),
(63, 'P30, 6.3", 4GB + 64GB, 12MP+20MP+8MP+13MP, (Dual S', 'Phones & Tabs', 'Some description for P30, 6.3", 4GB + 64GB, 12MP+20MP+8MP+13MP, (Dual SIM), Black. You won''t regret buying this product', 269, 19999, 23259, 'Cubot ', 2, 47, 'https://ke.jumia.is/0XfRxD_udDqSG_zj64o18BQ4l0o=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/93/747891/1.jpg?6019', '2019-06-11'),
(64, 'A3S Android 10 Global 3950mAh 5.7", 13MP Triple Sl', 'Phones & Tabs', 'Some description for A3S Android 10 Global 3950mAh 5.7", 13MP Triple Slots Dual 4G. You won''t regret buying this product', 24, 10590, 11673, 'UMIDIGI ', 1, 58, 'https://ke.jumia.is/0XfRxD_udDqSG_zj64o18BQ4l0o=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/93/747891/1.jpg?6019', '2019-06-11'),
(65, 'IPhone 4S Smartphone,3.5'''' Inches,32GB Rom+512MB R', 'Phones & Tabs', 'Some description for IPhone 4S Smartphone,3.5'''' Inches,32GB Rom+512MB Ram,8MP,Single Sim,Refurbished. You won''t regret buying this product', 189, 6990, 7676, 'Apple ', 1, 31, 'https://ke.jumia.is/0XfRxD_udDqSG_zj64o18BQ4l0o=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/93/747891/1.jpg?6019', '2019-06-11'),
(66, 'Earphones Silicone Case Cover Holder Black', 'Phones & Tabs', 'Some description for Earphones Silicone Case Cover Holder Black. You won''t regret buying this product', 109, 297, 331, 'Generic ', 1, 58, 'https://ke.jumia.is/BnOsqZxgVDAz668VzuZNG_aCaO0=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/30/578352/1.jpg?3048', '2019-11-02'),
(67, 'Sports Wireless Headset Bluetooth Earphone Black', 'Phones & Tabs', 'Some description for Sports Wireless Headset Bluetooth Earphone Black. You won''t regret buying this product', 237, 500, 598, 'Generic ', 1, 1, 'https://ke.jumia.is/XAFxE8Q0GYa9bgh32NiZPhFTkxs=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/23/296152/1.jpg?3689', '2019-06-22'),
(68, 'Sports Wireless Headset Bluetooth Earphone Black', 'Phones & Tabs', 'Some description for Sports Wireless Headset Bluetooth Earphone Black. You won''t regret buying this product', 237, 500, 598, 'Generic ', 1, 1, 'https://ke.jumia.is/XAFxE8Q0GYa9bgh32NiZPhFTkxs=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/23/296152/1.jpg?3689', '2019-06-22'),
(69, 'Note 7 Pro, 6.1", 3 GB + 32 GB, (Dual SIM), Twilig', 'Phones & Tabs', 'Some description for Note 7 Pro, 6.1", 3 GB + 32 GB, (Dual SIM), Twilight. You won''t regret buying this product', 21, 9999, 11759, 'Ulefone ', 3, 8, 'https://ke.jumia.is/XAFxE8Q0GYa9bgh32NiZPhFTkxs=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/23/296152/1.jpg?3689', '2019-06-22'),
(70, 'Redmi 8A, 6.22 Inches, 2 GB + 32 GB (Dual SIM) - B', 'Phones & Tabs', 'Some description for Redmi 8A, 6.22 Inches, 2 GB + 32 GB (Dual SIM) - Black. You won''t regret buying this product', 187, 11299, 11374, 'XIAOMI ', 3, 51, 'https://ke.jumia.is/kRYaz8qLih8I4rypH8EAt7m2J-s=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/93/988152/1.jpg?8485', '2019-08-09'),
(71, 'Mzooca Wired Small Earphones', 'Phones & Tabs', 'Some description for Mzooca Wired Small Earphones. You won''t regret buying this product', 154, 1000, 1196, 'Pace ', 3, 53, 'https://ke.jumia.is/VStb6Yilro9oEJRtwd3p6sjhmr4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/26/679391/1.jpg?0987', '2019-02-01'),
(72, 'Bluetooth Headset  Wireless Headphones Sports Head', 'Phones & Tabs', 'Some description for Bluetooth Headset  Wireless Headphones Sports Headset Black. You won''t regret buying this product', 44, 600, 715, 'Generic ', 5, 41, 'https://ke.jumia.is/3LI61QwB8G-KIMmVNoH0zfNIvlE=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/81/241801/1.jpg?7673', '2019-05-27'),
(73, 'IPhone 6 Plus 5.5'''' 64GB IOS Smartphone With Finge', 'Phones & Tabs', 'Some description for IPhone 6 Plus 5.5'''' 64GB IOS Smartphone With Fingerprint (Refurbished）- Grey. You won''t regret buying this product', 74, 50000, 52298, 'Apple ', 3, 42, 'https://ke.jumia.is/3LI61QwB8G-KIMmVNoH0zfNIvlE=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/81/241801/1.jpg?7673', '2019-05-27'),
(74, 'IPhone 6 Plus 5.5'''' 64GB IOS Smartphone With Finge', 'Phones & Tabs', 'Some description for IPhone 6 Plus 5.5'''' 64GB IOS Smartphone With Fingerprint (Refurbished）- Grey. You won''t regret buying this product', 74, 50000, 52298, 'Apple ', 3, 42, 'https://ke.jumia.is/3LI61QwB8G-KIMmVNoH0zfNIvlE=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/81/241801/1.jpg?7673', '2019-05-27'),
(75, 'IPhone 6 Plus 5.5'''' 1GB+64GB ROM IOS 4G Smartphone', 'Phones & Tabs', 'Some description for IPhone 6 Plus 5.5'''' 1GB+64GB ROM IOS 4G Smartphone With Fingerprint Sensor-Gold. You won''t regret buying this product', 212, 62000, 65922, 'Apple ', 2, 23, 'https://ke.jumia.is/3LI61QwB8G-KIMmVNoH0zfNIvlE=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/81/241801/1.jpg?7673', '2019-05-27'),
(76, '32GB Memory Card, Micro SD/TF Card - Red/Black', 'Phones & Tabs', 'Some description for 32GB Memory Card, Micro SD/TF Card - Red/Black. You won''t regret buying this product', 100, 978, 1165, 'IDCHIP ', 2, 0, 'https://ke.jumia.is/2qvHwB5bbTXo3PV3D1NomSU5VJM=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/70/673472/1.jpg?1107', '2019-06-29'),
(77, 'S18+,  6.4'''', 2 GB + 16 GB (Dual SIM) 3000mAh - Wh', 'Phones & Tabs', 'Some description for S18+,  6.4'''', 2 GB + 16 GB (Dual SIM) 3000mAh - White. You won''t regret buying this product', 283, 22600, 26736, 'Generic ', 4, 34, 'https://ke.jumia.is/2qvHwB5bbTXo3PV3D1NomSU5VJM=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/70/673472/1.jpg?1107', '2019-06-29'),
(78, 'IPhone 7 - 4.7" Smartphone RAM 2G + ROM 32G - 12MP', 'Phones & Tabs', 'Some description for IPhone 7 - 4.7" Smartphone RAM 2G + ROM 32G - 12MP - Single SIM - Matte Black. You won''t regret buying this product', 143, 47800, 54805, 'Apple ', 4, 6, 'https://ke.jumia.is/2qvHwB5bbTXo3PV3D1NomSU5VJM=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/70/673472/1.jpg?1107', '2019-06-29'),
(79, 'Pop 3, 16 GB+1GB RAM, 5.7" - 3500mAh,CPU: Quad-cor', 'Phones & Tabs', 'Some description for Pop 3, 16 GB+1GB RAM, 5.7" - 3500mAh,CPU: Quad-core 1.3GHz Dual SIM - Black. You won''t regret buying this product', 152, 10000, 11996, 'Tecno ', 4, 53, 'https://ke.jumia.is/2qvHwB5bbTXo3PV3D1NomSU5VJM=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/70/673472/1.jpg?1107', '2019-06-29'),
(80, 'Pop 3, 16 GB+1GB RAM, 5.7" - 3500mAh,CPU: Quad-cor', 'Phones & Tabs', 'Some description for Pop 3, 16 GB+1GB RAM, 5.7" - 3500mAh,CPU: Quad-core 1.3GHz Dual SIM - Black. You won''t regret buying this product', 152, 10000, 11996, 'Tecno ', 4, 53, 'https://ke.jumia.is/2qvHwB5bbTXo3PV3D1NomSU5VJM=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/70/673472/1.jpg?1107', '2019-06-29'),
(81, 'A3 5.5-Inch (2+16GB ROM) 12MP + 5MP Dual SIM 4G Sm', 'Phones & Tabs', 'Some description for A3 5.5-Inch (2+16GB ROM) 12MP + 5MP Dual SIM 4G Smartphone SpaceGray. You won''t regret buying this product', 252, 8999, 9574, 'UMIDIGI ', 5, 30, 'https://ke.jumia.is/GnGhqtUgNSaF17IFTDiQlUNPCkU=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/84/382191/1.jpg?6333', '2020-03-08'),
(82, 'Selfie Stick for smartphones,Get Two Free Earphone', 'Phones & Tabs', 'Some description for Selfie Stick for smartphones,Get Two Free Earphones. You won''t regret buying this product', 282, 599, 622, 'Annov ', 4, 52, 'https://ke.jumia.is/hQmf5wZLIHMY4MegtbH-w5oSHLU=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/99/039532/1.jpg?4187', '2019-12-09'),
(83, 'Galaxy S7 Edge Smartphone - 5.5" - 4GB RAM + 32GB ', 'Phones & Tabs', 'Some description for Galaxy S7 Edge Smartphone - 5.5" - 4GB RAM + 32GB ROM 12MP Single SIM - Gold. You won''t regret buying this product', 99, 40980, 46630, 'Samsung ', 2, 55, 'https://ke.jumia.is/hQmf5wZLIHMY4MegtbH-w5oSHLU=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/99/039532/1.jpg?4187', '2019-12-09'),
(84, 'Spark 4 Air,  6.1", 2GB + 32GB, 3000 MAh (Dual SIM', 'Phones & Tabs', 'Some description for Spark 4 Air,  6.1", 2GB + 32GB, 3000 MAh (Dual SIM) - Black. You won''t regret buying this product', 62, 11999, 12243, 'Tecno ', 2, 33, 'https://ke.jumia.is/hQmf5wZLIHMY4MegtbH-w5oSHLU=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/99/039532/1.jpg?4187', '2019-12-09'),
(85, 'Y7 Prime (2019), 6.26", 32 GB + 3 GB, (Dual SIM) -', 'Phones & Tabs', 'Some description for Y7 Prime (2019), 6.26", 32 GB + 3 GB, (Dual SIM) - Black. You won''t regret buying this product', 118, 15499, 17556, 'Huawei ', 2, 50, 'https://ke.jumia.is/hQmf5wZLIHMY4MegtbH-w5oSHLU=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/99/039532/1.jpg?4187', '2019-12-09'),
(86, 'Y7 Prime (2019), 6.26", 32 GB + 3 GB, (Dual SIM) -', 'Phones & Tabs', 'Some description for Y7 Prime (2019), 6.26", 32 GB + 3 GB, (Dual SIM) - Black. You won''t regret buying this product', 118, 15499, 17556, 'Huawei ', 2, 50, 'https://ke.jumia.is/hQmf5wZLIHMY4MegtbH-w5oSHLU=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/99/039532/1.jpg?4187', '2019-12-09'),
(87, 'R15 Pro 6.26"(3GB+32GB) Android 9 8MP+13MP Smartph', 'Phones & Tabs', 'Some description for R15 Pro 6.26"(3GB+32GB) Android 9 8MP+13MP Smartphone -Green. You won''t regret buying this product', 103, 9999, 11579, 'Cubot ', 1, 36, 'https://ke.jumia.is/hQmf5wZLIHMY4MegtbH-w5oSHLU=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/99/039532/1.jpg?4187', '2019-12-09'),
(88, 'Spark 4 Air 6.1", 32GB+2GB RAM+EXTRA 3000mAh Batte', 'Phones & Tabs', 'Some description for Spark 4 Air 6.1", 32GB+2GB RAM+EXTRA 3000mAh Battery- Black +Ringstern. You won''t regret buying this product', 268, 18000, 18960, 'Tecno ', 3, 7, 'https://ke.jumia.is/hQmf5wZLIHMY4MegtbH-w5oSHLU=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/99/039532/1.jpg?4187', '2019-12-09'),
(89, 'Wireless Bluetooth Headphone Durable Stereo Headse', 'Phones & Tabs', 'Some description for Wireless Bluetooth Headphone Durable Stereo Headset Earphone For Mobile Phone Black. You won''t regret buying this product', 237, 1368, 1565, 'Generic ', 4, 19, 'https://ke.jumia.is/NBPd-Rl7g7fxINXBBIIEP_aOiTc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/52/210421/1.jpg?8646', '2020-03-16'),
(90, 'Redmi 8A, 6.22 Inches, 2 GB + 32 GB (Dual SIM) - B', 'Phones & Tabs', 'Some description for Redmi 8A, 6.22 Inches, 2 GB + 32 GB (Dual SIM) - Blue. You won''t regret buying this product', 262, 11299, 12193, 'XIAOMI ', 5, 30, 'https://ke.jumia.is/ZaRjFknXJpEfh1IU1809R35kEGg=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/14/988152/1.jpg?8545', '2019-01-10'),
(91, 'Spark 4 Air 6.1", 6000 mAh, 2GB + 32GB, Dual 4G - ', 'Phones & Tabs', 'Some description for Spark 4 Air 6.1", 6000 mAh, 2GB + 32GB, Dual 4G - Nebula Black + Free Cover & 3D Glass. You won''t regret buying this product', 41, 12000, 12839, 'Tecno ', 3, 9, 'https://ke.jumia.is/ZaRjFknXJpEfh1IU1809R35kEGg=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/14/988152/1.jpg?8545', '2019-01-10'),
(92, 'Spark 4 Air 6.1", 6000 mAh, 2GB + 32GB, Dual 4G - ', 'Phones & Tabs', 'Some description for Spark 4 Air 6.1", 6000 mAh, 2GB + 32GB, Dual 4G - Nebula Black + Free Cover & 3D Glass. You won''t regret buying this product', 41, 12000, 12839, 'Tecno ', 3, 9, 'https://ke.jumia.is/ZaRjFknXJpEfh1IU1809R35kEGg=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/14/988152/1.jpg?8545', '2019-01-10'),
(93, 'MobilePower 6000mAh - Torch Li Battery K517 Black', 'Phones & Tabs', 'Some description for MobilePower 6000mAh - Torch Li Battery K517 Black. You won''t regret buying this product', 184, 800, 832, 'Spadger ', 1, 54, 'https://ke.jumia.is/otVCdsr_g-FwhVrhsSCM9f2ozEo=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/17/546641/1.jpg?3507', '2020-02-25'),
(94, 'Huawei Y9 Prime 2019 Case - Black With Blue', 'Phones & Tabs', 'Some description for Huawei Y9 Prime 2019 Case - Black With Blue. You won''t regret buying this product', 170, 899, 999, 'Generic ', 5, 55, 'https://ke.jumia.is/eSDEq0QZ4oOVDc_IHW5lXOMy9ls=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/55/417342/1.jpg?6621', '2019-08-18'),
(95, 'Camon 12 Air, 6.55", 32 GB + 3 GB, (Dual SIM) - Go', 'Phones & Tabs', 'Some description for Camon 12 Air, 6.55", 32 GB + 3 GB, (Dual SIM) - Gold + FREE COVER & GLASS. You won''t regret buying this product', 127, 22000, 23021, 'Tecno ', 3, 30, 'https://ke.jumia.is/eSDEq0QZ4oOVDc_IHW5lXOMy9ls=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/55/417342/1.jpg?6621', '2019-08-18'),
(96, 'In-ear Music Headset Wireless Sports Earphones', 'Phones & Tabs', 'Some description for In-ear Music Headset Wireless Sports Earphones. You won''t regret buying this product', 133, 399, 456, 'Generic ', 5, 47, 'https://ke.jumia.is/sG7-NlArXOirAQ-7XOFfsoZYdLo=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/25/992972/1.jpg?6161', '2019-04-07'),
(97, 'Live Bluetooth Wireless Headphones - Black', 'Electronics', 'Some description for Live Bluetooth Wireless Headphones - Black. You won''t regret buying this product', 75, 3201, 3450, 'Pace ', 2, 2, 'https://ke.jumia.is/dUcXyWX7xNnK4S28JfH1pyrTTrs=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/26/246591/1.jpg?6110', '2020-03-05'),
(98, 'Live Bluetooth Wireless Headphones - Black', 'Electronics', 'Some description for Live Bluetooth Wireless Headphones - Black. You won''t regret buying this product', 75, 3201, 3450, 'Pace ', 2, 2, 'https://ke.jumia.is/dUcXyWX7xNnK4S28JfH1pyrTTrs=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/26/246591/1.jpg?6110', '2020-03-05'),
(99, 'LE32P33DO; 32'''' Digital; HD; LED TV; Black.', 'Electronics', 'Some description for LE32P33DO; 32'''' Digital; HD; LED TV; Black.. You won''t regret buying this product', 55, 19499, 20418, 'SmartView ', 5, 52, 'https://ke.jumia.is/dUcXyWX7xNnK4S28JfH1pyrTTrs=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/26/246591/1.jpg?6110', '2020-03-05'),
(100, '32'''' Digital LED TV LE32 - Black', 'Electronics', 'Some description for 32'''' Digital LED TV LE32 - Black. You won''t regret buying this product', 84, 16999, 18250, 'Haier ', 4, 29, 'https://ke.jumia.is/dUcXyWX7xNnK4S28JfH1pyrTTrs=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/26/246591/1.jpg?6110', '2020-03-05'),
(101, 'Wireless Portable Speaker AUX - Black', 'Electronics', 'Some description for Wireless Portable Speaker AUX - Black. You won''t regret buying this product', 145, 1680, 1993, 'Generic ', 2, 30, 'https://ke.jumia.is/c-L4cbdnh45nhNlxa2DMR2JbxPc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/18/911352/1.jpg?0788', '2019-03-02'),
(102, 'IC-1303  3.1 X-Base HI-FI Bluetooth Speaker Black', 'Electronics', 'Some description for IC-1303  3.1 X-Base HI-FI Bluetooth Speaker Black. You won''t regret buying this product', 112, 1680, 1993, 'Generic ', 2, 30, 'https://ke.jumia.is/c-L4cbdnh45nhNlxa2DMR2JbxPc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/18/911352/1.jpg?0788', '2019-03-02'),
(103, 'Wall Bracket for 14" to 42" TV - Black', 'Electronics', 'Some description for Wall Bracket for 14" to 42" TV - Black. You won''t regret buying this product', 293, 756, 902, 'Generic ', 1, 37, 'https://ke.jumia.is/c-L4cbdnh45nhNlxa2DMR2JbxPc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/18/911352/1.jpg?0788', '2019-03-02'),
(104, 'Wall Bracket for 14" to 42" TV - Black', 'Electronics', 'Some description for Wall Bracket for 14" to 42" TV - Black. You won''t regret buying this product', 293, 756, 902, 'Generic ', 1, 37, 'https://ke.jumia.is/c-L4cbdnh45nhNlxa2DMR2JbxPc=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/18/911352/1.jpg?0788', '2019-03-02'),
(105, '4 Way Extension Cable - White', 'Electronics', 'Some description for 4 Way Extension Cable - White. You won''t regret buying this product', 109, 600, 694, 'Power King ', 3, 14, 'https://ke.jumia.is/t5exU56Vmak8LF-9N39JH-oqu1I=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/15/244852/1.jpg?0210', '2019-05-11'),
(106, '32TB7000 32" Frameless Smart Android LED TV - Blac', 'Electronics', 'Some description for 32TB7000 32" Frameless Smart Android LED TV - Black. You won''t regret buying this product', 271, 16499, 18909, 'Skyworth ', 5, 24, 'https://ke.jumia.is/t5exU56Vmak8LF-9N39JH-oqu1I=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/15/244852/1.jpg?0210', '2019-05-11'),
(107, 'Digital TV Aerial With 10mtrs Cable Plus Free Cabl', 'Electronics', 'Some description for Digital TV Aerial With 10mtrs Cable Plus Free Cable Clips. You won''t regret buying this product', 198, 1400, 1666, 'Generic ', 4, 12, 'https://ke.jumia.is/xvwo2UYwv52D9dg1cBdtdrSdHfo=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/23/781472/1.jpg?7866', '2019-11-28'),
(108, 'High Quality 6 Way Extension+ Free 2 Way Extension', 'Electronics', 'Some description for High Quality 6 Way Extension+ Free 2 Way Extension. You won''t regret buying this product', 64, 1000, 1098, 'Generic ', 5, 40, 'https://ke.jumia.is/MbaIUQxdcfQZlKkOuUZOPF3rEAI=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/83/916602/1.jpg?8357', '2019-08-22'),
(109, 'BTF - 32HDTSP, 32", LED SMART & Digital TV - Black', 'Electronics', 'Some description for BTF - 32HDTSP, 32", LED SMART & Digital TV - Black. You won''t regret buying this product', 83, 23686, 26776, 'Bruhm ', 1, 44, 'https://ke.jumia.is/MbaIUQxdcfQZlKkOuUZOPF3rEAI=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/83/916602/1.jpg?8357', '2019-08-22'),
(110, 'BTF - 32HDTSP, 32", LED SMART & Digital TV - Black', 'Electronics', 'Some description for BTF - 32HDTSP, 32", LED SMART & Digital TV - Black. You won''t regret buying this product', 83, 23686, 26776, 'Bruhm ', 1, 44, 'https://ke.jumia.is/MbaIUQxdcfQZlKkOuUZOPF3rEAI=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/83/916602/1.jpg?8357', '2019-08-22'),
(111, '40'''', Full HD Digital TV - Black', 'Electronics', 'Some description for 40'''', Full HD Digital TV - Black. You won''t regret buying this product', 286, 26000, 29127, 'UKA ', 3, 5, 'https://ke.jumia.is/MbaIUQxdcfQZlKkOuUZOPF3rEAI=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/83/916602/1.jpg?8357', '2019-08-22'),
(112, 'VP8832S 32" - HD Android Smart TV + Free Wall Moun', 'Electronics', 'Some description for VP8832S 32" - HD Android Smart TV + Free Wall Mount - Black. You won''t regret buying this product', 96, 26000, 29127, 'UKA ', 3, 5, 'https://ke.jumia.is/MbaIUQxdcfQZlKkOuUZOPF3rEAI=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/83/916602/1.jpg?8357', '2019-08-22'),
(113, 'LS-421A Multimedia Speaker System With Bluetooth', 'Electronics', 'Some description for LS-421A Multimedia Speaker System With Bluetooth. You won''t regret buying this product', 300, 3399, 3673, 'TAGWOOD ', 4, 52, 'https://ke.jumia.is/0qjPoBUDNMzpY0F7e4sBaOlpLrw=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/06/303972/1.jpg?4964', '2019-07-24'),
(114, 'P15 Bluetooth Headset -Free OTG cable + Earphones', 'Electronics', 'Some description for P15 Bluetooth Headset -Free OTG cable + Earphones. You won''t regret buying this product', 113, 1399, 1650, 'Pangpai ', 3, 24, 'https://ke.jumia.is/fAf2u21Y4A3sGVOHO7vnRii2hc4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/12/370462/1.jpg?6265', '2020-02-25'),
(115, 'LS-421B Multimedia Speaker System 2.1', 'Electronics', 'Some description for LS-421B Multimedia Speaker System 2.1. You won''t regret buying this product', 106, 2999, 3060, 'TAGWOOD ', 3, 41, 'https://ke.jumia.is/6M6xarqNXing53ftQmz5mqCGBBQ=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/37/303972/1.jpg?8267', '2019-10-03'),
(116, 'LS-421B Multimedia Speaker System 2.1', 'Electronics', 'Some description for LS-421B Multimedia Speaker System 2.1. You won''t regret buying this product', 106, 2999, 3060, 'TAGWOOD ', 3, 41, 'https://ke.jumia.is/6M6xarqNXing53ftQmz5mqCGBBQ=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/37/303972/1.jpg?8267', '2019-10-03'),
(117, 'Decoder+Outdoor Gotenna +1 M Gotv Max Subscription', 'Electronics', 'Some description for Decoder+Outdoor Gotenna +1 M Gotv Max Subscription. You won''t regret buying this product', 94, 2999, 3337, 'Gotv ', 2, 33, 'https://ke.jumia.is/28__XTg7HXpIamAeOH6a24ILC08=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/25/396672/1.jpg?5207', '2019-03-27'),
(118, '50" - UHD Smart TV - Black', 'Electronics', 'Some description for 50" - UHD Smart TV - Black. You won''t regret buying this product', 99, 47000, 54772, 'UKA ', 5, 35, 'https://ke.jumia.is/28__XTg7HXpIamAeOH6a24ILC08=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/25/396672/1.jpg?5207', '2019-03-27'),
(119, 'Fridge Guard  With Free Tv Guard Mk - White', 'Electronics', 'Some description for Fridge Guard  With Free Tv Guard Mk - White. You won''t regret buying this product', 117, 1600, 1659, 'Generic ', 5, 27, 'https://ke.jumia.is/D401SEz12W2uQY2TDFs9DiHFlfQ=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/98/604531/1.jpg?9347', '2019-03-06'),
(120, 'Extension Cable - 6 Way - White.', 'Electronics', 'Some description for Extension Cable - 6 Way - White.. You won''t regret buying this product', 131, 800, 894, 'Rk Trust ', 1, 31, 'https://ke.jumia.is/1cCgJyC-euWrZcWVHndIan_ebjA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/23/741531/1.jpg?1221', '2020-01-19'),
(121, '22" - Digital LED TV - Black..', 'Electronics', 'Some description for 22" - Digital LED TV - Black... You won''t regret buying this product', 114, 800, 894, 'Rk Trust ', 1, 31, 'https://ke.jumia.is/1cCgJyC-euWrZcWVHndIan_ebjA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/23/741531/1.jpg?1221', '2020-01-19'),
(122, '22" - Digital LED TV - Black..', 'Electronics', 'Some description for 22" - Digital LED TV - Black... You won''t regret buying this product', 114, 800, 894, 'Rk Trust ', 1, 31, 'https://ke.jumia.is/1cCgJyC-euWrZcWVHndIan_ebjA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/23/741531/1.jpg?1221', '2020-01-19'),
(123, '43'''' - Full HD - Digital TV - Black', 'Electronics', 'Some description for 43'''' - Full HD - Digital TV - Black. You won''t regret buying this product', 226, 32000, 32731, 'UKA ', 3, 23, 'https://ke.jumia.is/1cCgJyC-euWrZcWVHndIan_ebjA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/23/741531/1.jpg?1221', '2020-01-19'),
(124, 'VP8832S, 32", Frame-less Smart Android LED TV - Bl', 'Electronics', 'Some description for VP8832S, 32", Frame-less Smart Android LED TV - Black. You won''t regret buying this product', 245, 32000, 32731, 'UKA ', 3, 23, 'https://ke.jumia.is/1cCgJyC-euWrZcWVHndIan_ebjA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/23/741531/1.jpg?1221', '2020-01-19'),
(125, 'Subwoofer,Hitech MultiMedia SpeakerSystem-FM-USB,W', 'Electronics', 'Some description for Subwoofer,Hitech MultiMedia SpeakerSystem-FM-USB,Wireless Bluetooth,USB,FM-10,000 watts. You won''t regret buying this product', 76, 8000, 9284, 'Royal Sound ', 2, 51, 'https://ke.jumia.is/wzlMDEOY0wSXCbSyq3bdLn9mxek=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/35/488772/1.jpg?9465', '2019-10-26'),
(126, 'TV Guard + Free Fridge Guard And 2-Way Cable', 'Electronics', 'Some description for TV Guard + Free Fridge Guard And 2-Way Cable. You won''t regret buying this product', 119, 2000, 2005, 'Generic ', 4, 48, 'https://ke.jumia.is/lsvdjzsz6vIMKtX7YcBjBO2oLYI=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/16/699562/1.jpg?7844', '2019-11-04'),
(127, '3M HDMI Cable - Black,Get Two Pairs Free Earphones', 'Electronics', 'Some description for 3M HDMI Cable - Black,Get Two Pairs Free Earphones. You won''t regret buying this product', 263, 699, 714, 'Annov ', 5, 2, 'https://ke.jumia.is/NCPHc1sfC0drVl_RX-WwSMSjVbA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/52/697861/1.jpg?8829', '2019-10-31'),
(128, '3M HDMI Cable - Black,Get Two Pairs Free Earphones', 'Electronics', 'Some description for 3M HDMI Cable - Black,Get Two Pairs Free Earphones. You won''t regret buying this product', 263, 699, 714, 'Annov ', 5, 2, 'https://ke.jumia.is/NCPHc1sfC0drVl_RX-WwSMSjVbA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/52/697861/1.jpg?8829', '2019-10-31'),
(129, '40", FHD Smart LED TV - Black.', 'Electronics', 'Some description for 40", FHD Smart LED TV - Black.. You won''t regret buying this product', 68, 31000, 33965, 'UKA ', 5, 26, 'https://ke.jumia.is/NCPHc1sfC0drVl_RX-WwSMSjVbA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/52/697861/1.jpg?8829', '2019-10-31'),
(130, 'BFP- 32LETW - 32" - Slim LED Digital TV - Black.', 'Electronics', 'Some description for BFP- 32LETW - 32" - Slim LED Digital TV - Black.. You won''t regret buying this product', 56, 19900, 20256, 'Bruhm ', 4, 14, 'https://ke.jumia.is/NCPHc1sfC0drVl_RX-WwSMSjVbA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/52/697861/1.jpg?8829', '2019-10-31'),
(131, '4-Way  Extension Cable - White', 'Electronics', 'Some description for 4-Way  Extension Cable - White. You won''t regret buying this product', 274, 1000, 1167, 'Astra ', 3, 42, 'https://ke.jumia.is/jvA1LwmTolyzfXl-a176ev9ort0=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/69/588481/1.jpg?6638', '2019-07-08'),
(132, 'Digital TV Aerial - Multicolor', 'Electronics', 'Some description for Digital TV Aerial - Multicolor. You won''t regret buying this product', 250, 1200, 1275, 'Phelistar ', 5, 30, 'https://ke.jumia.is/I7p61vay_Fd7IeD-SAro6U41fkg=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/24/6333/1.jpg?3059', '2019-03-13'),
(133, 'Tv Guard +  Wall Mounting Bracket for 14 - 42 TV  ', 'Electronics', 'Some description for Tv Guard +  Wall Mounting Bracket for 14 - 42 TV  plus free 32GB FLASH DISK. You won''t regret buying this product', 290, 3930, 4490, 'POWERSKY ', 1, 36, 'https://ke.jumia.is/3kYbdMDGT0n1EWlAi_hM2qXuBKw=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/25/437722/1.jpg?8414', '2019-12-21'),
(134, 'Tv Guard +  Wall Mounting Bracket for 14 - 42 TV  ', 'Electronics', 'Some description for Tv Guard +  Wall Mounting Bracket for 14 - 42 TV  plus free 32GB FLASH DISK. You won''t regret buying this product', 290, 3930, 4490, 'POWERSKY ', 1, 36, 'https://ke.jumia.is/3kYbdMDGT0n1EWlAi_hM2qXuBKw=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/25/437722/1.jpg?8414', '2019-12-21'),
(135, '3.1 12000W MULTIMEDIA SUBWOOFER SPEAKER(bluetooth,', 'Electronics', 'Some description for 3.1 12000W MULTIMEDIA SUBWOOFER SPEAKER(bluetooth,USB,SD card,Fm,DVD). You won''t regret buying this product', 208, 9999, 11579, 'Ampex ', 5, 40, 'https://ke.jumia.is/Kr5_hY9iCXuTRu3hPZo30SPZVac=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/89/827752/1.jpg?0977', '2019-09-06'),
(136, 'Decoder + 1 Months Gotv Max Subscription - Black', 'Electronics', 'Some description for Decoder + 1 Months Gotv Max Subscription - Black. You won''t regret buying this product', 195, 1999, 2152, 'Gotv ', 3, 5, 'https://ke.jumia.is/d8zS7lmSyii1GmWyoQStmFC1Bps=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/15/988462/1.jpg?5065', '2019-03-11'),
(137, '43" FHD Smart LED TV - Black', 'Electronics', 'Some description for 43" FHD Smart LED TV - Black. You won''t regret buying this product', 156, 34000, 35728, 'UKA ', 5, 36, 'https://ke.jumia.is/d8zS7lmSyii1GmWyoQStmFC1Bps=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/15/988462/1.jpg?5065', '2019-03-11');
INSERT INTO `products` (`product_id`, `product_name`, `category`, `description`, `quantity`, `original_price`, `price`, `seller_name`, `rating`, `percentage_strike`, `product_image`, `date_added`) VALUES
(138, '8Pcs Assorted colors Infant Newborn Bath Towel,Was', 'Baby Products', 'Some description for 8Pcs Assorted colors Infant Newborn Bath Towel,Wash cloth. You won''t regret buying this product', 158, 390, 464, 'Generic ', 1, 38, 'https://ke.jumia.is/4T6WZp6WI9mfD--wyOw4rXO8Z5Y=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/22/480881/1.jpg?4399', '2019-02-14'),
(139, 'Babywipes Newborn Pure 56''s', 'Baby Products', 'Some description for Babywipes Newborn Pure 56''s. You won''t regret buying this product', 42, 390, 464, 'Generic ', 1, 38, 'https://ke.jumia.is/4T6WZp6WI9mfD--wyOw4rXO8Z5Y=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/22/480881/1.jpg?4399', '2019-02-14'),
(140, 'Baby Dry Diapers, Size 2 (3-8kg), Jumbo Pack (Coun', 'Baby Products', 'Some description for Baby Dry Diapers, Size 2 (3-8kg), Jumbo Pack (Count 80). You won''t regret buying this product', 228, 1400, 1638, 'Pampers ', 4, 1, 'https://ke.jumia.is/6ZE9nvLEgLWOm7q6_uULooOp22U=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/48/996931/1.jpg?7657', '2020-02-25'),
(141, 'Baby Dry Diapers With Extra Absorb Channels-Size 4', 'Baby Products', 'Some description for Baby Dry Diapers With Extra Absorb Channels-Size 4(64 Count). You won''t regret buying this product', 159, 1400, 1474, 'Pampers ', 3, 51, 'https://ke.jumia.is/6MOE-VMlY523bMmRR5as39UAq1c=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/23/30016/1.jpg?7636', '2019-09-03'),
(142, 'Waterproof Soft Baby Bibs- Set Of 7', 'Baby Products', 'Some description for Waterproof Soft Baby Bibs- Set Of 7. You won''t regret buying this product', 175, 550, 631, 'Generic ', 2, 52, 'https://ke.jumia.is/lhlyHWYYFcxFNYXYM8a3s_IE5bk=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/26/8909/1.jpg?0657', '2019-01-30'),
(143, 'Waterproof Soft Baby Bibs- Set Of 7', 'Baby Products', 'Some description for Waterproof Soft Baby Bibs- Set Of 7. You won''t regret buying this product', 175, 550, 631, 'Generic ', 2, 52, 'https://ke.jumia.is/lhlyHWYYFcxFNYXYM8a3s_IE5bk=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/26/8909/1.jpg?0657', '2019-01-30'),
(144, 'Baby Dry Diapers (11-25kg) - Size 5 (60 Count).', 'Baby Products', 'Some description for Baby Dry Diapers (11-25kg) - Size 5 (60 Count).. You won''t regret buying this product', 293, 1400, 1629, 'Pampers ', 5, 7, 'https://ke.jumia.is/wwZHgLNxbQUk3npoWLKksmQtbx8=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/10/33695/1.jpg?7680', '2019-03-06'),
(145, 'Cerelac Wheat 50G ', 'Baby Products', 'Some description for Cerelac Wheat 50G . You won''t regret buying this product', 136, 1400, 1629, 'Pampers ', 5, 7, 'https://ke.jumia.is/wwZHgLNxbQUk3npoWLKksmQtbx8=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/10/33695/1.jpg?7680', '2019-03-06'),
(146, 'Mild and Gentle Baby soap - 100g', 'Baby Products', 'Some description for Mild and Gentle Baby soap - 100g. You won''t regret buying this product', 269, 1400, 1629, 'Pampers ', 5, 7, 'https://ke.jumia.is/wwZHgLNxbQUk3npoWLKksmQtbx8=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/10/33695/1.jpg?7680', '2019-03-06'),
(147, 'Wipes Flip Top Pack A/Vera 72''s', 'Baby Products', 'Some description for Wipes Flip Top Pack A/Vera 72''s. You won''t regret buying this product', 274, 1400, 1629, 'Pampers ', 5, 7, 'https://ke.jumia.is/wwZHgLNxbQUk3npoWLKksmQtbx8=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/10/33695/1.jpg?7680', '2019-03-06'),
(148, 'MOLFIX BABY PANTS JUMBO SIZE 4 (MAXI) COUNT 60 PIE', 'Baby Products', 'Some description for MOLFIX BABY PANTS JUMBO SIZE 4 (MAXI) COUNT 60 PIECES. You won''t regret buying this product', 215, 1090, 1231, 'Molfix ', 3, 23, 'https://ke.jumia.is/Q_yrVPVYk-A_AtWYjMDzbf_HcmI=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/15/056112/1.jpg?7488', '2020-02-04'),
(149, 'MOLFIX BABY PANTS JUMBO SIZE 4 (MAXI) COUNT 60 PIE', 'Baby Products', 'Some description for MOLFIX BABY PANTS JUMBO SIZE 4 (MAXI) COUNT 60 PIECES. You won''t regret buying this product', 215, 1090, 1231, 'Molfix ', 3, 23, 'https://ke.jumia.is/Q_yrVPVYk-A_AtWYjMDzbf_HcmI=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/15/056112/1.jpg?7488', '2020-02-04'),
(150, 'Portable & Foldable Baby Bassinet/Sleeping Nest/ C', 'Baby Products', 'Some description for Portable & Foldable Baby Bassinet/Sleeping Nest/ Cot/ Mosquito Net - Blue. You won''t regret buying this product', 38, 1500, 1527, 'Fashion ', 2, 36, 'https://ke.jumia.is/Q_yrVPVYk-A_AtWYjMDzbf_HcmI=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/15/056112/1.jpg?7488', '2020-02-04'),
(151, 'Dry Comfort Diapers', 'Baby Products', 'Some description for Dry Comfort Diapers. You won''t regret buying this product', 75, 1571, 1666, 'Huggies ', 1, 42, 'https://ke.jumia.is/PZxiI3JBUYso01bOg7uTQZthQJg=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/87/816831/1.jpg?1899', '2019-12-07'),
(152, '8Pcs Assorted Infant Feeding Wipe Cloth Soft - Mul', 'Baby Products', 'Some description for 8Pcs Assorted Infant Feeding Wipe Cloth Soft - Multicolour. You won''t regret buying this product', 171, 400, 402, 'Generic ', 4, 43, 'https://ke.jumia.is/eN6mlBdALUHG8aOJoxJhdUwJu90=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/34/879481/1.jpg?0477', '2019-07-17'),
(153, 'Diaper Pants, Size 3 (6-11kg), Jumbo Pack (Count 6', 'Baby Products', 'Some description for Diaper Pants, Size 3 (6-11kg), Jumbo Pack (Count 62). You won''t regret buying this product', 121, 1400, 1599, 'Pampers ', 5, 42, 'https://ke.jumia.is/RIVICzj5upG-sRQOCH6rm4RXKzw=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/45/06823/1.jpg?7751', '2019-04-10'),
(154, 'Soft Velvet Baby Mackintosh / Changing Mat / Urine', 'Baby Products', 'Some description for Soft Velvet Baby Mackintosh / Changing Mat / Urine pad. You won''t regret buying this product', 222, 1000, 1017, 'Generic ', 5, 22, 'https://ke.jumia.is/q0Wb3dVgxC3Fnk5zmKbcmjFJb-A=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/68/152142/1.jpg?2468', '2019-11-20'),
(155, 'Soft Velvet Baby Mackintosh / Changing Mat / Urine', 'Baby Products', 'Some description for Soft Velvet Baby Mackintosh / Changing Mat / Urine pad. You won''t regret buying this product', 222, 1000, 1017, 'Generic ', 5, 22, 'https://ke.jumia.is/q0Wb3dVgxC3Fnk5zmKbcmjFJb-A=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/68/152142/1.jpg?2468', '2019-11-20'),
(156, 'Baby Dry Diapers, Size 3, Jumbo Pack (Count 72)', 'Baby Products', 'Some description for Baby Dry Diapers, Size 3, Jumbo Pack (Count 72). You won''t regret buying this product', 212, 1400, 1635, 'Pampers ', 5, 7, 'https://ke.jumia.is/0eMG_h2dXn28kXH-Fy6Ei_PI6Oo=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/82/358422/1.jpg?7613', '2019-09-06'),
(157, 'New Born, size 1, 2-5Kg 28S', 'Baby Products', 'Some description for New Born, size 1, 2-5Kg 28S. You won''t regret buying this product', 125, 1400, 1635, 'Pampers ', 5, 7, 'https://ke.jumia.is/0eMG_h2dXn28kXH-Fy6Ei_PI6Oo=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/82/358422/1.jpg?7613', '2019-09-06'),
(158, 'Pants Size 6 - 48 Pieces', 'Baby Products', 'Some description for Pants Size 6 - 48 Pieces. You won''t regret buying this product', 286, 1400, 1635, 'Pampers ', 5, 7, 'https://ke.jumia.is/0eMG_h2dXn28kXH-Fy6Ei_PI6Oo=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/82/358422/1.jpg?7613', '2019-09-06'),
(159, 'BABY PANTS JUMBO SIZE 5 (JUNIOR) COUNT 56 PIECES', 'Baby Products', 'Some description for BABY PANTS JUMBO SIZE 5 (JUNIOR) COUNT 56 PIECES. You won''t regret buying this product', 202, 1090, 1164, 'Molfix ', 3, 27, 'https://ke.jumia.is/EZp8C77Z8TW7xgtCFqRZ8Gc8LzA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/47/056112/1.jpg?7847', '2019-03-24'),
(160, 'All Over Clean Wipes – 56''s', 'Baby Products', 'Some description for All Over Clean Wipes – 56''s. You won''t regret buying this product', 173, 195, 206, 'Huggies ', 2, 54, 'https://ke.jumia.is/EZp8C77Z8TW7xgtCFqRZ8Gc8LzA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/47/056112/1.jpg?7847', '2019-03-24'),
(161, 'All Over Clean Wipes – 56''s', 'Baby Products', 'Some description for All Over Clean Wipes – 56''s. You won''t regret buying this product', 173, 195, 206, 'Huggies ', 2, 54, 'https://ke.jumia.is/EZp8C77Z8TW7xgtCFqRZ8Gc8LzA=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/47/056112/1.jpg?7847', '2019-03-24'),
(162, 'MACINTOSH - Baby Waterproof Urine pad / Changing m', 'Baby Products', 'Some description for MACINTOSH - Baby Waterproof Urine pad / Changing mat. You won''t regret buying this product', 275, 1200, 1418, 'Generic ', 1, 29, 'https://ke.jumia.is/cGbwflQbIl7krAhLTchMmpGyTfQ=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/98/006672/1.jpg?8067', '2020-02-20'),
(163, 'Baby Carrier With a Hood - Brown', 'Baby Products', 'Some description for Baby Carrier With a Hood - Brown. You won''t regret buying this product', 265, 1800, 2125, 'Generic ', 1, 27, 'https://ke.jumia.is/sBPyGjVlsEd2lOV61aQZxHfQ1Cw=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/36/4833/1.jpg?6121', '2019-03-28'),
(164, '6 Washable/ Reusable Avent Breast Pads Includes La', 'Baby Products', 'Some description for 6 Washable/ Reusable Avent Breast Pads Includes Laundry Bag. You won''t regret buying this product', 103, 750, 813, 'Generic ', 3, 8, 'https://ke.jumia.is/8uXltxnRKBorouXZf58bM4Mi70Q=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/28/799981/1.jpg?4659', '2019-01-09'),
(165, 'Baby Mild & Gentle Powder 50g + 50%', 'Baby Products', 'Some description for Baby Mild & Gentle Powder 50g + 50%. You won''t regret buying this product', 182, 750, 813, 'Generic ', 3, 8, 'https://ke.jumia.is/8uXltxnRKBorouXZf58bM4Mi70Q=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/28/799981/1.jpg?4659', '2019-01-09'),
(166, '4pcs Cotton Baby Shawl Green Blue Newborn Baby  Un', 'Baby Products', 'Some description for 4pcs Cotton Baby Shawl Green Blue Newborn Baby  Unisex  Receiving Blanket. You won''t regret buying this product', 85, 1500, 1785, 'Generic ', 4, 58, 'https://ke.jumia.is/Fq01Wmom1gh7NT4pQsDsPXInSgY=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/92/406691/1.jpg?4645', '2019-06-09'),
(167, '4pcs Cotton Baby Shawl Green Blue Newborn Baby  Un', 'Baby Products', 'Some description for 4pcs Cotton Baby Shawl Green Blue Newborn Baby  Unisex  Receiving Blanket. You won''t regret buying this product', 85, 1500, 1785, 'Generic ', 4, 58, 'https://ke.jumia.is/Fq01Wmom1gh7NT4pQsDsPXInSgY=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/92/406691/1.jpg?4645', '2019-06-09'),
(168, 'Intelligent Double Electric Breast Pump - BPA FREE', 'Baby Products', 'Some description for Intelligent Double Electric Breast Pump - BPA FREE. You won''t regret buying this product', 132, 8000, 9180, 'Generic ', 1, 40, 'https://ke.jumia.is/h8pJtwirpVB3if1A40r7jKVHL-Y=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/91/946701/1.jpg?1846', '2019-02-04'),
(169, 'Large Breathable Baby Carrier with Hip Seat.', 'Baby Products', 'Some description for Large Breathable Baby Carrier with Hip Seat.. You won''t regret buying this product', 125, 2317, 2525, 'Imama ', 2, 39, 'https://ke.jumia.is/TQs6mf1VzE8WVgtR7ajQF12rMwM=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/14/339971/1.jpg?1094', '2019-10-03'),
(170, 'Nestle Nan 3 Dha Probio Bb Milk- 400g', 'Baby Products', 'Some description for Nestle Nan 3 Dha Probio Bb Milk- 400g. You won''t regret buying this product', 142, 2317, 2525, 'Imama ', 2, 39, 'https://ke.jumia.is/TQs6mf1VzE8WVgtR7ajQF12rMwM=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/14/339971/1.jpg?1094', '2019-10-03'),
(171, '4Pcs Boys Newborn Swaddlers Cute receiving blanket', 'Baby Products', 'Some description for 4Pcs Boys Newborn Swaddlers Cute receiving blanket multi purpose Baby Flannels cotton shawl. You won''t regret buying this product', 120, 1300, 1458, 'Generic ', 5, 45, 'https://ke.jumia.is/aA44Aqv97zGvplj3A-rZad-OO8g=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/03/928571/1.jpg?0073', '2019-11-13'),
(172, 'Baby Oil Soap 100g', 'Baby Products', 'Some description for Baby Oil Soap 100g. You won''t regret buying this product', 277, 1300, 1458, 'Generic ', 5, 45, 'https://ke.jumia.is/aA44Aqv97zGvplj3A-rZad-OO8g=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/03/928571/1.jpg?0073', '2019-11-13'),
(173, 'Baby Oil Soap 100g', 'Baby Products', 'Some description for Baby Oil Soap 100g. You won''t regret buying this product', 277, 1300, 1458, 'Generic ', 5, 45, 'https://ke.jumia.is/aA44Aqv97zGvplj3A-rZad-OO8g=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/03/928571/1.jpg?0073', '2019-11-13'),
(174, '4PCS Baby Suit For Infant Cap+Sweater+Pant+Socks', 'Baby Products', 'Some description for 4PCS Baby Suit For Infant Cap+Sweater+Pant+Socks. You won''t regret buying this product', 256, 1000, 1087, 'Fashion ', 4, 52, 'https://ke.jumia.is/AnoCMC5i873e9LmHo7hVGSnJZXk=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/01/591772/1.jpg?6505', '2019-12-09'),
(175, 'Elegant new design 5 in 1 Baby Diaper Bag Nappy Ch', 'Baby Products', 'Some description for Elegant new design 5 in 1 Baby Diaper Bag Nappy Changing Pad waterproof Travel Mummy Bag. You won''t regret buying this product', 84, 1800, 2049, 'Generic ', 3, 56, 'https://ke.jumia.is/FFYMPUFhaTByG2FoGA9bUfEl9q0=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/77/79919/1.jpg?0666', '2020-01-13'),
(176, 'New born towels', 'Baby Products', 'Some description for New born towels. You won''t regret buying this product', 265, 599, 600, 'Generic ', 1, 46, 'https://ke.jumia.is/K-hp4cRi7EYmYTvfg6CirTpkME4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/31/986772/1.jpg?5525', '2019-01-21'),
(177, 'Sensitive Wipes 72''s', 'Baby Products', 'Some description for Sensitive Wipes 72''s. You won''t regret buying this product', 24, 599, 600, 'Generic ', 1, 46, 'https://ke.jumia.is/K-hp4cRi7EYmYTvfg6CirTpkME4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/31/986772/1.jpg?5525', '2019-01-21'),
(178, 'Baby Dry Diapers, Size 2 (3-8kg), Jumbo Pack (Coun', 'Baby Products', 'Some description for Baby Dry Diapers, Size 2 (3-8kg), Jumbo Pack (Count 80). You won''t regret buying this product', 207, 1400, 1481, 'Pampers ', 3, 27, 'https://ke.jumia.is/bvE90abdXon5EIUPBA4eInCrvl4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/92/358422/1.jpg?7772', '2019-12-18'),
(179, 'Baby Dry Diapers, Size 2 (3-8kg), Jumbo Pack (Coun', 'Baby Products', 'Some description for Baby Dry Diapers, Size 2 (3-8kg), Jumbo Pack (Count 80). You won''t regret buying this product', 207, 1400, 1481, 'Pampers ', 3, 27, 'https://ke.jumia.is/bvE90abdXon5EIUPBA4eInCrvl4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/92/358422/1.jpg?7772', '2019-12-18'),
(180, 'Nan 1 Baby Milk- 400g', 'Baby Products', 'Some description for Nan 1 Baby Milk- 400g. You won''t regret buying this product', 119, 1400, 1481, 'Pampers ', 3, 27, 'https://ke.jumia.is/bvE90abdXon5EIUPBA4eInCrvl4=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/92/358422/1.jpg?7772', '2019-12-18'),
(181, 'Washable Reusable Adjustable Baby Diaper with 2 In', 'Baby Products', 'Some description for Washable Reusable Adjustable Baby Diaper with 2 Inserts. You won''t regret buying this product', 176, 429, 461, 'Generic ', 4, 11, 'https://ke.jumia.is/iuVzLYvEhablDsUFCnK76FPgXbI=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/75/588672/1.jpg?8825', '2019-06-24'),
(182, 'Warm silky Baby Shawl Blanket - Cream White', 'Baby Products', 'Some description for Warm silky Baby Shawl Blanket - Cream White. You won''t regret buying this product', 48, 1944, 2049, 'Generic ', 3, 59, 'https://ke.jumia.is/TghZTuGBetYMsBsUNgRTnRKdb_w=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/89/342142/1.jpg?2285', '2019-04-03'),
(183, 'Carters 5 Pack Assorted Cotton Baby Boy Pants in D', 'Baby Products', 'Some description for Carters 5 Pack Assorted Cotton Baby Boy Pants in Different colors. You won''t regret buying this product', 182, 1944, 2049, 'Generic ', 3, 59, 'https://ke.jumia.is/TghZTuGBetYMsBsUNgRTnRKdb_w=/fit-in/220x220/filters:fill(white):sharpen(1,0,false):quality(100)/product/89/342142/1.jpg?2285', '2019-04-03');

-- --------------------------------------------------------

--
-- Table structure for table `product_categories`
--

CREATE TABLE `product_categories` (
  `category_id` int(20) NOT NULL,
  `category_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product_categories`
--

INSERT INTO `product_categories` (`category_id`, `category_name`) VALUES
(1, 'Food & Groceries'),
(2, 'Phones & Tabs'),
(3, 'Electronics'),
(4, 'Baby Products'),
(5, 'Services');

-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

CREATE TABLE `purchases` (
  `purchase_id` int(20) NOT NULL,
  `cart_id` int(20) NOT NULL,
  `product_id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `product_quantity` int(4) NOT NULL,
  `price` int(7) NOT NULL,
  `order_code` varchar(20) NOT NULL,
  `d_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delivered` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `app_users`
--
ALTER TABLE `app_users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cart_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `product_categories`
--
ALTER TABLE `product_categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`purchase_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `app_users`
--
ALTER TABLE `app_users`
  MODIFY `user_id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cart_id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=184;
--
-- AUTO_INCREMENT for table `product_categories`
--
ALTER TABLE `product_categories`
  MODIFY `category_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `purchase_id` int(20) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
